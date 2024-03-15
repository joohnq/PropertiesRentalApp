package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joohnq.propertiesrentalapp.R
import com.joohnq.propertiesrentalapp.util.Origin
import com.joohnq.propertiesrentalapp.util.TextInputVerification
import com.joohnq.propertiesrentalapp.view.theme.Blue1A1E25
import com.joohnq.propertiesrentalapp.view.theme.GradientGrayE3E3E7
import com.joohnq.propertiesrentalapp.view.theme.GradientPurpleToPurple
import com.joohnq.propertiesrentalapp.view.theme.GradientRedFF1717
import com.joohnq.propertiesrentalapp.view.theme.Gray7D7F88
import com.joohnq.propertiesrentalapp.view.theme.GrayF2F2F2
import com.joohnq.propertiesrentalapp.view.theme.Purple6246EA
import com.joohnq.propertiesrentalapp.view.theme.PurpleF1F1FE
import com.joohnq.propertiesrentalapp.view.theme.PurpleF2F2F3
import com.joohnq.propertiesrentalapp.view.theme.RedFEF1F1
import com.joohnq.propertiesrentalapp.view.theme.RedFF1717

@Composable
fun CustomTextField(
    modifier: Modifier,
    colors: TextFieldColors,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
    value: String,
    shape: Shape,
    isError: Boolean,
    isErrorText: String,
    textStyle: TextStyle,
    placeholder: @Composable (() -> Unit),
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    onValueChange: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            modifier = modifier,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            value = value,
            placeholder = placeholder,
            isError = isError,
            shape = shape,
            colors = colors,
            keyboardOptions = keyboardOptions,
            onValueChange = onValueChange,
            visualTransformation = visualTransformation,
            textStyle = textStyle,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            if (isError || isErrorText.isNotEmpty()) isErrorText else "",
            style = p_12_semibold_inter.copy(color = RedFF1717)
        )
    }

}

@Composable
fun CustomTextFieldNormal(
    value: String,
    isError: Boolean = false,
    placeholder: @Composable (() -> Unit),
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onFocusChanged: (Boolean, Boolean) -> Unit,
    isErrorText: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {
    val shape = RoundedCornerShape(30.dp)
    val focus = remember { mutableStateOf(false) }
    CustomTextField(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.5.dp,
                brush = if (isError)
                    GradientRedFF1717
                else (
                        if (focus.value)
                            GradientPurpleToPurple
                        else
                            GradientGrayE3E3E7
                        ),
                shape = shape
            )
            .background(color = GrayF2F2F2, shape = shape)
            .onFocusChanged {
                with(it) {
                    focus.value = isFocused
                    onFocusChanged(isFocused, hasFocus)
                }
            },
        leadingIcon = leadingIcon,
        value = value,
        shape = shape,
        isError = isError,
        trailingIcon = trailingIcon,
        textStyle = p_16_normal_fs.copy(color = Blue1A1E25),
        placeholder = placeholder,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedTextColor = Blue1A1E25,
            unfocusedTextColor = Gray7D7F88,
            focusedLeadingIconColor = Purple6246EA,
            unfocusedLeadingIconColor = Gray7D7F88,
            focusedPlaceholderColor = Purple6246EA,
            unfocusedPlaceholderColor = Gray7D7F88,
            focusedLabelColor = Purple6246EA,
            unfocusedLabelColor = Gray7D7F88,
            unfocusedContainerColor = PurpleF2F2F3,
            focusedContainerColor = PurpleF1F1FE,
            focusedTrailingIconColor = Purple6246EA,
            unfocusedTrailingIconColor = Gray7D7F88,
            errorTextColor = RedFF1717,
            errorPlaceholderColor = RedFF1717,
            errorLeadingIconColor = RedFF1717,
            errorLabelColor = RedFF1717,
            errorTrailingIconColor = RedFF1717,
            errorBorderColor = RedFF1717,
            errorCursorColor = RedFF1717,
            errorContainerColor = RedFEF1F1,
        ),
        isErrorText = isErrorText,
        onValueChange = onValueChange
    )
}

@Composable
fun TextFieldEmail(
    email: String,
    onEmailChange: (String) -> Unit,
    onEmailErrorChange: (Boolean, String) -> Unit,
    emailError: Boolean,
    emailErrorText: String
) {
    CustomTextFieldNormal(
        value = email,
        placeholder = { Text(stringResource(id = R.string.insert_your_email)) },
        isError = emailError,
        isErrorText = emailErrorText,
        onFocusChanged = { state: Boolean, hasFocus: Boolean ->
            onEmailErrorChange(false, "")
            if (!state && hasFocus) {
                TextInputVerification.verifyEmail(email = email, onEmailError = onEmailErrorChange)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            autoCorrect = true,
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = stringResource(id = R.string.email)
            )
        }) { e: String -> onEmailChange(e) }
}

@Composable
fun TextFieldPassword(
    password: String,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    onPasswordErrorChange: (Boolean, String) -> Unit,
    passwordError: Boolean,
    passwordErrorText: String,
    passwordVisibility: Boolean,
    onPasswordChange: (String) -> Unit,
) {
    CustomTextFieldNormal(
        value = password,
        placeholder = { Text(stringResource(id = R.string.insert_your_password)) },
        isError = passwordError,
        isErrorText = passwordErrorText,
        onFocusChanged = { state: Boolean, hasFocus: Boolean ->
            onPasswordErrorChange(false, "")
            if (!state && hasFocus) {
                TextInputVerification.verifyPassword(
                    password = password,
                    origin = Origin.REGISTER,
                    onPasswordError = onPasswordErrorChange
                )
            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            autoCorrect = false,
        ),
        trailingIcon = {
            IconButton(onClick = { onPasswordVisibilityChange(!passwordVisibility) }) {
                Icon(
                    painter = painterResource(id = if (passwordVisibility) R.drawable.ic_eye_open else R.drawable.ic_eye_close),
                    contentDescription = stringResource(id = R.string.password_hide_show)
                )
            }
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_key),
                contentDescription = stringResource(id = R.string.password)
            )
        }) { p: String -> onPasswordChange(p) }
}

@Composable
fun SearchBarWithFilter(
    text: String,
    onTextChange: (String) -> Unit,
    onFilterButtonIsClicked: () -> Unit,
    onFocusChange: (Boolean, Boolean) -> Unit
) {
    CustomTextFieldNormal(
        value = text,
        placeholder = {
            Text(
                stringResource(id = R.string.search_address_city_location),
                style = p_16_normal_fs
            )
        },
        onFocusChanged = { state: Boolean, hasFocus: Boolean ->
            onFocusChange(state, hasFocus)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = true,
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Done,
        ),
        trailingIcon = {
            IconButton(onClick = { onFilterButtonIsClicked }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = stringResource(id = R.string.filter)
                )
            }
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(id = R.string.search)
            )
        }) { p: String -> onTextChange(p) }
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    Column {
        CustomTextFieldNormal(
            "",
            placeholder = { Text("Label") },
            onFocusChanged = { _, _ ->
                println("Foi")
            }) {
            println("Foi")
        }
        SearchBarWithFilter("", {}, {}, { _, _ -> })
    }
}