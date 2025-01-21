package dev.lkeeeey.edu.library.presentation.teachers.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import dev.lkeeeey.edu.core.presentation.Theme
import dev.lkeeeey.edu.library.domain.models.TeacherModel
import ecucateme.composeapp.generated.resources.Bold
import ecucateme.composeapp.generated.resources.Res
import ecucateme.composeapp.generated.resources.Thin
import ecucateme.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TeacherCard (
    teacher: TeacherModel,
    onClick: (TeacherModel) -> Unit
) {
    Row  (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp)
            .clip(
                RoundedCornerShape(12.dp)
            )
            .background(
                color = White,
                shape = RoundedCornerShape(
                    size = 8.dp
                )
            )
            .padding(16.dp)
            .clickable {
                onClick(teacher)
            },
        horizontalArrangement = Arrangement.Start
    ) {
//        Image(
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f),
//            painter = painterResource(Res.drawable.ic_calendar_no_plans),
//            contentDescription = "image of teacher",
//            alignment = Alignment.Center
//        )

        AsyncImage(
            model = if (teacher.username == "lkey_teacher") Res.getUri("drawable/profile.png") else "https://downloader.disk.yandex.ru/preview/46692cb7596a50382d2b6a7c1a95c4830e6118140bc8e5d7a6222b21c351cdf3/678c2ea9/L8I5E3oS6TafT9afc-0-5uxlpZngX6nt_bNlMdf3hH7OxqHSDfd9AIGp8Aaf3h4Zyql1GFSCeIKLWOeDNJjHHA%3D%3D?uid=0&filename=empty.png&disposition=inline&hash=&limit=0&content_type=image%2Fpng&owner_uid=0&tknv=v2&size=2048x2048",
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(CircleShape),
            alignment = Alignment.Center
        )

        Column (
           modifier = Modifier
               .fillMaxWidth()
               .weight(1f)
        ) {
            Text(
                text = teacher.name,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(Res.font.Bold)),
                    fontWeight = FontWeight(400),
                    color = Theme.colors.blackProfile,
                    letterSpacing = 0.3.sp,
                )
            )

            Text(
                text = teacher.subject,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(Res.font.Thin)),
                    fontWeight = FontWeight(400),
                    color = Theme.colors.blackProfile,
                    letterSpacing = 0.3.sp,
                )
            )
        }
    }
}
