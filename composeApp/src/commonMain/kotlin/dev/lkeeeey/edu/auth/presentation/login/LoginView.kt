package dev.lkeeeey.edu.auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.auth.presentation.login.viewmodel.LoginAction
import dev.lkeeeey.edu.auth.presentation.login.viewmodel.LoginState
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.core.presentation.components.btn.FilledBtn
import dev.lkeeeey.edu.core.presentation.components.fields.OutlinedText
import dev.lkeeeey.edu.core.presentation.components.fields.PasswordField
import dev.lkeeeey.edu.core.presentation.components.text.FooterAuth
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import ecucateme.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource


@Composable
fun LoginView(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {

    Column(
        modifier = Modifier
            .background(Theme.colors.secondaryBackground)
            .fillMaxSize()
            .blur(if (state.isLoading) 4.dp else 0.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image (
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(Res.drawable.logo),
                contentDescription = "image auth my olimp"
            )

            Spacer(modifier = Modifier.padding(top = 9.dp))

            Text(
                text = "Войти в сервис",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(Res.font.Thin)),
                    fontWeight = FontWeight(400),
                    color = Theme.colors.defaultText,
                    textAlign = TextAlign.Center,
                )
            )

            Spacer(modifier = Modifier.height(22.dp))

            OutlinedText(
                previousData = state.username,
                label = "Телеграмм",
            ) {
                onAction(LoginAction.OnUsernameChanged(it))
            }

            Spacer(modifier = Modifier.height(12.dp))

            PasswordField(
                previousData = state.password,
                label = "Пароль",
                isError = state.isPasswordError,
                errorText = state.errorMessage
            ) {
                onAction(LoginAction.OnPasswordChanged(it))
            }

            Spacer(modifier = Modifier.height(24.dp))

            FilledBtn(
                padding = 0.dp,
                isEnabled = state.isLoading,
                text = "Войти",
            ) {
                onAction(LoginAction.OnLogin)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .clickable {
                        // TODO navigate to register
//                        navigator.navigate(PassEmailScreenDestination)
                    },
                text = "Забыл пароль",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(Res.font.Thin)),
                    fontWeight = FontWeight(400),
                    color = Theme.colors.blackProfile,
                    textAlign = TextAlign.Center
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Theme.colors.secondaryBackground)
                .padding(horizontal = 54.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FooterAuth(
                content = "Еще не регистрировались в сервисах EducateMe?",
                offer = "Зарегистрировать учетную запись"
            ) {
                // TODO navigate to sign up
            }
        }
    }

}
