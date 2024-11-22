package dev.lkeeeey.edu.auth.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkeeeey.edu.auth.presentation.register.viewmodel.RegisterAction
import dev.lkeeeey.edu.auth.presentation.register.viewmodel.RegisterState
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.core.presentation.components.btn.FilledBtn
import dev.lkeeeey.edu.core.presentation.components.fields.OutlinedText
import dev.lkeeeey.edu.core.presentation.components.fields.PasswordField
import dev.lkeeeey.edu.core.presentation.components.text.FooterAuth
import dev.lkeeeey.edu.core.presentation.components.text.Header
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import org.jetbrains.compose.resources.Font

@Composable
fun RegisterView (
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    Column (
        modifier = Modifier
            .background(Theme.colors.secondaryBackground)
            .fillMaxSize()
            .blur(if (state.isLoading) 4.dp else 0.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Header(
                actionText = "Для создания  учетной записи укажите свои данные:"
            )

            if (state.isError) {
                Text(
                    text = state.errorMessage,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(Res.font.Thin)),
                        fontWeight = FontWeight(400),
                        color = Theme.colors.errorColor,
                        textAlign = TextAlign.Center
                    )
                )
            }

            OutlinedText(
                previousData = "",
                label = "Телеграмм",
            ) {
                onAction(RegisterAction.OnUsernameChanged(it))
            }

            Spacer(modifier = Modifier.height(12.dp))

            PasswordField(
                previousData = "",
                label = "Пароль",
            ) {
                onAction(RegisterAction.OnPasswordChanged(it))
            }

            Spacer(modifier = Modifier.height(12.dp))

            PasswordField(
                previousData = "",
                label = "Подвтерждение пароля",
                isError = state.isError,
                errorText = state.errorMessage
            ) {
                onAction(RegisterAction.OnConfirmedPasswordChanged(it))
            }

            Spacer(modifier = Modifier.height(16.dp))

            FilledBtn(
                padding = 0.dp,
                isEnabled = state.isButtonEnabled,
                text = "Зарегистрироваться",
            ) {
                onAction(RegisterAction.OnSignUp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Регистрируясь, вы соглашаетесь на обработку персональных данных и получение информационных сообщений от группы компании EducateMe",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(Res.font.Thin)),
                    fontWeight = FontWeight(400),
                    color = Theme.colors.blackProfile,
                    textAlign = TextAlign.Center
                )
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Theme.colors.secondaryScreen)
                .padding(horizontal = 54.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FooterAuth(
                content = "Уже регистрировались в сервисах EducateMe?",
                offer = "Войдите в учетную запись"
            ) {
                onAction(RegisterAction.OnLogin)
            }
        }
    }
}
