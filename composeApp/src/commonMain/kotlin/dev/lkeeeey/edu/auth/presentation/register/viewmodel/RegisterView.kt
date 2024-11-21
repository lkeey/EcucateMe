package dev.lkeeeey.edu.auth.presentation.register.viewmodel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterView (
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    Column (
        modifier = Modifier
            .background(Transparent)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.auth_my_olimp),
                contentDescription = "image auth my olimp"
            )

            Spacer(modifier = Modifier.padding(top = 9.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 54.dp),
                text = "Для создания  учетной записи укажите свои данные:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.regular)),
                    fontWeight = FontWeight(400),
                    color = BlackProfile,
                    textAlign = TextAlign.Center,
                )
            )

            Spacer(modifier = Modifier.height(22.dp))

            OutlinedText(
                previousData = "",
                label = stringResource(id = R.string.email),
                isError = state.value.isEmailError,
                errorText = stringResource(R.string.email_error_already_in_use)
            ) {
                viewModel.onEvent(SignUpEvent.OnEmailUpdated(it))
            }

            Spacer(modifier = Modifier.height(12.dp))

            PasswordField(
                previousData = "",
                label = stringResource(id = R.string.password),
            ) {
                viewModel.onEvent(SignUpEvent.OnPasswordUpdated(it))
            }

            Spacer(modifier = Modifier.height(6.dp))

            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp)),
                progress = state.value.passwordStatus,
                trackColor = BackgroundProgressIndicator,
                color = when (state.value.passwordStatus) {
                    1f -> SuccessStatus
                    0.3f -> ErrorStatus
                    0.6f -> MiddleStatus
                    else -> BackgroundProgressIndicator
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            PasswordField(
                previousData = "",
                label = stringResource(id = R.string.confirm_password),
                isError = state.value.passwordError != null,
                errorText = state.value.passwordError ?: ""
            ) {
                viewModel.onEvent(SignUpEvent.OnConfirmedPasswordUpdated(it))
            }

            Spacer(modifier = Modifier.height(16.dp))

            FilledBtn(
                padding = 0.dp,
                isEnabled = state.value.isRegistering,
                text = stringResource(id = R.string.register),
            ) {
                viewModel.onEvent(SignUpEvent.OnSignUp(navigator = navigator))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.poilitics),
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.regular)),
                    fontWeight = FontWeight(400),
                    color = BlackProfile,
                    textAlign = TextAlign.Center
                )
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = SecondaryScreen)
                .padding(horizontal = 54.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FooterAuth(
                content = stringResource(R.string.already_registered),
                offer = stringResource(R.string.login_to_account)
            ) {
                navigator.navigate(LoginScreenDestination)
            }
        }
    }
}
