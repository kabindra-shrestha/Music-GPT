package com.kabindra.musicgpt.presentation.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kabindra.musicgpt.presentation.ui.theme.inputFieldDefault
import com.kabindra.musicgpt.presentation.ui.theme.inputFieldError
import com.kabindra.musicgpt.presentation.ui.theme.inputFieldLabelDefault
import com.kabindra.musicgpt.presentation.ui.theme.inputFieldLabelError
import com.kabindra.musicgpt.presentation.ui.theme.inputFieldTextDefault
import com.kabindra.musicgpt.presentation.ui.theme.inputFieldTextError
import com.kabindra.musicgpt.presentation.ui.theme.labelColor
import com.kabindra.musicgpt.presentation.ui.theme.transparent

@Composable
fun CreateSongInputFields(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorText: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    leadingIcon: Int? = null,
    trialingIcon: Int? = null,
    isEnabled: Boolean = true,
    autoFocus: Boolean = false,
    gradientColors: List<Color>,
    borderWidth: Dp = 2.dp,
    cornerRadius: Dp = 12.dp,
    onClickLeadingIcon: () -> Unit,
    onClickTrailingIcon: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .drawBehind {
                    val strokeWidth = borderWidth.toPx()
                    val radius = cornerRadius.toPx()
                    val brush = Brush.horizontalGradient(colors = gradientColors)

                    // draw rounded rectangle border with gradient
                    drawRoundRect(
                        brush = brush,
                        size = size.copy(
                            width = size.width - strokeWidth,
                            height = size.height - strokeWidth
                        ),
                        cornerRadius = CornerRadius(radius, radius),
                        style = Stroke(width = strokeWidth)
                    )
                }
                .background(Color.Transparent, shape = RoundedCornerShape(cornerRadius))
        ) {
            val focusManager = LocalFocusManager.current

            val leadingIcons: (@Composable () -> Unit)? = leadingIcon?.let {
                {
                    ImageHandlerRes(
                        modifier = Modifier
                            .size(20.dp)
                            .aspectRatio(1f / 1f)
                            .clip(CircleShape),
                        image = it,
                        contentDescription = "",
                        isClickable = true,
                        onClick = { onClickLeadingIcon() }
                    )
                }
            }

            val trailingIcons: (@Composable () -> Unit)? = trialingIcon?.let {
                {
                    ImageHandlerRes(
                        modifier = Modifier
                            .size(20.dp)
                            .aspectRatio(1f / 1f)
                            .clip(CircleShape),
                        image = it,
                        contentDescription = "",
                        isClickable = true,
                        onClick = { onClickTrailingIcon() }
                    )
                }
            }

            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { TextComponent(text = label) },
                isError = isError && errorText.isNotEmpty(),
                modifier = modifier
                    .height(40.dp)
                    .focusRequester(focusRequester),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 14.sp,
                    lineHeight = 18.sp
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = transparent,   // remove underline
                    unfocusedIndicatorColor = transparent, // remove underline
                    disabledIndicatorColor = transparent,
                    errorIndicatorColor = transparent,
                    focusedContainerColor = transparent,   // no background
                    unfocusedContainerColor = transparent,
                    disabledContainerColor = transparent,
                    errorContainerColor = transparent
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions =
                    if (imeAction == ImeAction.Done) {
                        KeyboardActions(onDone = { focusManager.clearFocus() })
                    } else {
                        KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
                    },
                leadingIcon = leadingIcons,
                trailingIcon = trailingIcons,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                enabled = isEnabled,
                supportingText = {
                    if (!isError && errorText.isNotEmpty()) {
                        TextError(text = errorText, maxLines = 2)
                    }
                }
            )
        }
        if (!isError && errorText.isNotEmpty()) {
            AnimatedVisibility(
                visible = errorText.isNotEmpty(),
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> fullHeight }
                ) + fadeIn(),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight }
                ) + fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    TextError(text = errorText, maxLines = 2)
                }
            }
        }
    }

    // Auto-focus & show keyboard when this composable enters composition
    LaunchedEffect(Unit) {
        if (autoFocus) {
            focusRequester.requestFocus()
            keyboardController?.show()
        }
    }
}

@Composable
fun CreateSongInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorText: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    leadingIcon: Int? = null,
    trialingIcon: Int? = null,
    isEnabled: Boolean = true,
    autoFocus: Boolean = false,
    gradientColors: List<Color>,
    borderWidth: Dp = 2.dp,
    cornerRadius: Dp = 12.dp,
    onClickLeadingIcon: () -> Unit,
    onClickTrailingIcon: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
                lineHeight = 18.sp,
                color = if (isEnabled) labelColor() else labelColor()
            ),
            cursorBrush = SolidColor(labelColor()),
            singleLine = true,
            enabled = isEnabled,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions =
                if (imeAction == ImeAction.Done) {
                    KeyboardActions(onDone = { focusManager.clearFocus() })
                } else {
                    KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
                },
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth()
                .height(40.dp)
                .background(MaterialTheme.colorScheme.background),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .dropShadow(
                            shape = RoundedCornerShape(cornerRadius * 2),
                            shadow = Shadow(
                                radius = 4.dp,
                                spread = 6f.dp,
                                brush = Brush.horizontalGradient(
                                    gradientColors
                                ),
                                offset = DpOffset(x = 0.dp, y = 0.dp),
                                alpha = 1f
                            )
                        )
                        .clip(RoundedCornerShape(cornerRadius * 2))
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .drawBehind {
                                val strokeWidth = borderWidth.toPx()
                                val radius = cornerRadius.toPx()
                                val brush = Brush.horizontalGradient(colors = gradientColors)

                                drawRoundRect(
                                    brush = brush,
                                    size = size.copy(
                                        width = size.width - strokeWidth,
                                        height = size.height - strokeWidth
                                    ),
                                    cornerRadius = CornerRadius(radius, radius),
                                    style = Stroke(width = strokeWidth)
                                )
                            }
                            .background(Color.Transparent, shape = RoundedCornerShape(cornerRadius))
                            .padding(horizontal = 8.dp, vertical = 6.dp), // control padding here
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Leading icon
                            leadingIcon?.let {
                                ImageHandlerRes(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape),
                                    image = it,
                                    contentDescription = "",
                                    isClickable = true,
                                    onClick = { onClickLeadingIcon() }
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                            }

                            // Text field content or placeholder
                            if (value.isEmpty()) {
                                TextComponent(
                                    text = label,
                                    color = Color.Gray
                                )
                            }

                            Box(
                                modifier = Modifier.weight(1f)
                            ) {
                                innerTextField()
                            }

                            // Trailing icon
                            trialingIcon?.let {
                                Spacer(modifier = Modifier.width(6.dp))
                                ImageHandlerRes(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape),
                                    image = it,
                                    contentDescription = "",
                                    isClickable = true,
                                    onClick = { onClickTrailingIcon() }
                                )
                            }
                        }
                    }
                }
            }
        )

        // Error text OUTSIDE gradient box
        if (!isError && errorText.isNotEmpty()) {
            AnimatedVisibility(
                visible = errorText.isNotEmpty(),
                enter = slideInVertically { it } + fadeIn(),
                exit = slideOutVertically { it } + fadeOut()
            ) {
                Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)) {
                    TextError(text = errorText, maxLines = 2)
                }
            }
        }
    }

    // Auto-focus & show keyboard when entering composition
    LaunchedEffect(Unit) {
        if (autoFocus) {
            focusRequester.requestFocus()
            keyboardController?.show()
        }
    }
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorText: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    leadingIcon: ImageVector? = null,
    trialingIcon: ImageVector? = null,
    isEnabled: Boolean = true,
    onClickTrailingIcon: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    val leadingIcons: (@Composable () -> Unit)? = leadingIcon?.let {
        {
            ImageHandlerVector(
                modifier = Modifier
                    .size(20.dp)
                    .aspectRatio(1f / 1f),
                image = it,
                contentDescription = ""
            )
        }
    }

    val trailingIcons: (@Composable () -> Unit)? = trialingIcon?.let {
        {
            ImageHandlerVector(
                modifier = Modifier
                    .size(20.dp)
                    .aspectRatio(1f / 1f),
                image = it,
                contentDescription = "",
                isClickable = true,
                onClick = { onClickTrailingIcon() }
            )
        }
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { TextComponent(text = label) },
        isError = isError && errorText.isNotEmpty(),
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = inputFieldDefault,
            focusedTextColor = inputFieldTextDefault,
            focusedLabelColor = inputFieldLabelDefault,
            errorBorderColor = inputFieldError,
            errorTextColor = inputFieldTextError,
            errorLabelColor = inputFieldLabelError,
            disabledBorderColor = inputFieldDefault,
            disabledTextColor = inputFieldTextDefault,
            disabledLabelColor = inputFieldLabelDefault
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions =
            if (imeAction == ImeAction.Done) {
                KeyboardActions(onDone = { focusManager.clearFocus() })
            } else {
                KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
            },
        leadingIcon = leadingIcons,
        trailingIcon = trailingIcons,
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        enabled = isEnabled,
        supportingText = {
            if (!isError && errorText.isNotEmpty()) {
                TextError(text = errorText, maxLines = 2)
            }
        }
    )
}

@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    errorText: String = "",
    keyboardType: KeyboardType = KeyboardType.Password,
    imeAction: ImeAction = ImeAction.Next,
    leadingIcon: ImageVector? = null
) {
    val focusManager = LocalFocusManager.current
    var isPasswordVisible by remember { mutableStateOf(false) }

    val leadingIcons: (@Composable () -> Unit)? = leadingIcon?.let {
        {
            ImageHandlerVector(
                modifier = Modifier
                    .size(20.dp)
                    .aspectRatio(1f / 1f),
                image = it,
                contentDescription = ""
            )
        }
    }

    val trailingIcons = @Composable {
        ImageHandlerVector(
            modifier = Modifier
                .size(20.dp)
                .aspectRatio(1f / 1f),
            image = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
            contentDescription = "",
            onClick = { isPasswordVisible = !isPasswordVisible }
        )
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { TextComponent(text = label) },
        isError = !isError,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = inputFieldDefault,
            focusedTextColor = inputFieldTextDefault,
            focusedLabelColor = inputFieldLabelDefault,
            errorBorderColor = inputFieldError,
            errorTextColor = inputFieldTextError,
            errorLabelColor = inputFieldLabelError,
            disabledBorderColor = inputFieldDefault,
            disabledTextColor = inputFieldTextDefault,
            disabledLabelColor = inputFieldLabelDefault
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions =
            if (imeAction == ImeAction.Done) {
                KeyboardActions(onDone = { focusManager.clearFocus() })
            } else {
                KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
            },
        leadingIcon = leadingIcons,
        trailingIcon = trailingIcons,
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        supportingText = {
            if (!isError && errorText.isNotEmpty()) {
                TextError(text = errorText, maxLines = 2)
            }
        }
    )
}

@Composable
fun <T> DropdownField(
    modifier: Modifier = Modifier,      // Modifier for customization
    items: List<T>,                     // List of items to display
    itemContent: (T) -> String,         // Content for each item
    value: String,                      // Currently selected value
    selectedItem: T?,                   // Currently selected item
    onItemSelected: (T) -> Unit,        // Callback for item selection
    label: String,                      // Optional label
    isError: Boolean = false,           // Whether to show an error state
    errorText: String = "",             // Validation message
    leadingIcon: ImageVector? = null,
    isEnabled: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldWidth by remember { mutableStateOf(0) }

    val leadingIcons: (@Composable () -> Unit)? = leadingIcon?.let {
        {
            ImageHandlerVector(
                modifier = Modifier
                    .size(20.dp)
                    .aspectRatio(1f / 1f),
                image = it,
                contentDescription = ""
            )
        }
    }

    val trailingIcons: (@Composable () -> Unit) = {
        ImageHandlerVector(
            modifier = Modifier
                .size(20.dp)
                .aspectRatio(1f / 1f),
            image = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
            contentDescription = "",
            isClickable = false,
        )
    }
    // Dropdown Field with DropdownMenu positioned correctly
    Box(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = {}, // Disable user input as it's a dropdown
            readOnly = true, // Prevent manual text entry
            label = { TextComponent(text = label) },
            isError = isError,
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { textFieldWidth = it.width }
                .clickable { expanded = !expanded }, // Make the entire field clickable
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = inputFieldDefault,
                focusedTextColor = inputFieldTextDefault,
                focusedLabelColor = inputFieldLabelDefault,
                errorBorderColor = inputFieldError,
                errorTextColor = inputFieldTextError,
                errorLabelColor = inputFieldLabelError,
                disabledBorderColor = inputFieldDefault,
                disabledTextColor = inputFieldTextDefault,
                disabledLabelColor = inputFieldLabelDefault
            ),
            leadingIcon = leadingIcons,
            trailingIcon = trailingIcons,
            enabled = isEnabled,
            supportingText = {
                if (!isError && errorText.isNotEmpty()) {
                    TextError(text = errorText, maxLines = 2)
                }
            },
            // Remove the internal clickable behavior since it's handled by Box
            interactionSource = remember { MutableInteractionSource() }
        )

        // Dropdown menu
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldWidth.toDp() }) // Match the width of the OutlinedTextField
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    },
                    text = { TextComponent(text = itemContent(item)) }
                )
            }
        }
    }
}