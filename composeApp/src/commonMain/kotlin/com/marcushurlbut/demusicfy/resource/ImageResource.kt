package com.marcushurlbut.demusicfy.resource

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import demusicfy.composeapp.generated.resources.Res
import demusicfy.composeapp.generated.resources.app_icon
import demusicfy.composeapp.generated.resources.pause_circle_24px
import org.jetbrains.compose.resources.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import demusicfy.composeapp.generated.resources.graphic_eq_24px
import demusicfy.composeapp.generated.resources.menu_open_24px
import demusicfy.composeapp.generated.resources.play_circle_24px
import demusicfy.composeapp.generated.resources.metronome
import demusicfy.composeapp.generated.resources.remove_24px
import demusicfy.composeapp.generated.resources.add_24px
import demusicfy.composeapp.generated.resources.arrow_back_ios_24px
import demusicfy.composeapp.generated.resources.check_24px
import demusicfy.composeapp.generated.resources.check_box_24px
import demusicfy.composeapp.generated.resources.check_box_outline_blank_24px
import demusicfy.composeapp.generated.resources.close_24px
import demusicfy.composeapp.generated.resources.delete_24px
import demusicfy.composeapp.generated.resources.delete_forever_24px
import demusicfy.composeapp.generated.resources.edit_24px
import demusicfy.composeapp.generated.resources.edit_off_24px
import demusicfy.composeapp.generated.resources.edit_square_24px
import demusicfy.composeapp.generated.resources.music_note_24px
import demusicfy.composeapp.generated.resources.ink_eraser_24px
import demusicfy.composeapp.generated.resources.save_24px
import demusicfy.composeapp.generated.resources.folder_open_24px
import demusicfy.composeapp.generated.resources.gradient_music_notes_background
import demusicfy.composeapp.generated.resources.routine_24px
import demusicfy.composeapp.generated.resources.tooltip_24px

@Composable
fun getAppIconResource(): Painter {
    return painterResource(resource = Res.drawable.music_note_24px)
}

@Composable
fun PauseIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.pause_circle_24px)

    Icon(
        painter = painter,
        contentDescription = "Pause Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun EQIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.graphic_eq_24px)

    Icon(
        painter = painter,
        contentDescription = "EQ Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun MenuOpenIcon() {
    val painter = painterResource(resource = Res.drawable.menu_open_24px)

    Icon(
        painter = painter,
        contentDescription = "Menu Open Icon",
        modifier = Modifier.size(48.dp),
        tint = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
fun PlayIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.play_circle_24px)

    Icon(
        painter = painter,
        contentDescription = "Play Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun SubtractIcon() {
    val painter = painterResource(resource = Res.drawable.remove_24px)

    Icon(
        painter = painter,
        contentDescription = "Subtract Icon",
        modifier = Modifier.size(48.dp),
        tint = MaterialTheme.colorScheme.onPrimary
    )
}

@Composable
fun AddIcon(
    tint: Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.add_24px)

    Icon(
        painter = painter,
        contentDescription = "Add Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun ClearIcon(
    tint : Color = MaterialTheme.colorScheme.onPrimary,
    modifier: Modifier
) {
    val painter = painterResource(resource = Res.drawable.ink_eraser_24px)

    Icon(
        painter = painter,
        contentDescription = "Erase Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun SaveIcon(
    tint : Color = MaterialTheme.colorScheme.onPrimary,
    modifier: Modifier
) {
    val painter = painterResource(resource = Res.drawable.save_24px)

    Icon(
        painter = painter,
        contentDescription = "Erase Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun OpenFolderIcon(
    tint : Color = MaterialTheme.colorScheme.onPrimary,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.folder_open_24px)

    Icon(
        painter = painter,
        contentDescription = "Open Folder Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun ThemeIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.routine_24px)

    Icon(
        painter = painter,
        contentDescription = "Theme Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun CheckIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.check_24px)

    Icon(
        painter = painter,
        contentDescription = "Check Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun CloseIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.close_24px)

    Icon(
        painter = painter,
        contentDescription = "Close Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun EditBoxIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.edit_square_24px)

    Icon(
        painter = painter,
        contentDescription = "Edit Box Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun EditIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.edit_24px)

    Icon(
        painter = painter,
        contentDescription = "Edit Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun EditOffIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.edit_off_24px)

    Icon(
        painter = painter,
        contentDescription = "Edit Off Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun DeleteIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.delete_24px)

    Icon(
        painter = painter,
        contentDescription = "Delete Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun DeleteForeverIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.delete_forever_24px)

    Icon(
        painter = painter,
        contentDescription = "Delete Forever Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun BackArrowIOS(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.arrow_back_ios_24px)

    Icon(
        painter = painter,
        contentDescription = "iOS Style Back Arrow Icon",
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun ToolTipIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.tooltip_24px)

    Icon(
        painter = painter,
        contentDescription = "Tool Tip Icon",
        modifier = Modifier,
        tint = tint
    )
}

@Composable
fun CheckBoxFilledIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.check_box_24px)

    Icon(
        painter = painter,
        contentDescription = "Check Box Filled Icon",
        modifier = Modifier,
        tint = tint
    )
}

@Composable
fun CheckBoxBlankIcon(
    tint : Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val painter = painterResource(resource = Res.drawable.check_box_outline_blank_24px)

    Icon(
        painter = painter,
        contentDescription = "Check Box Blank Icon",
        modifier = Modifier,
        tint = tint
    )
}



// Images

@Composable
fun MetronomeImage() : Painter {
    return painterResource(resource = Res.drawable.metronome)
}

@Composable
fun MusicNotesBackgroundImage() : Painter {
    return  painterResource(resource = Res.drawable.gradient_music_notes_background)
}


