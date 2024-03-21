package com.joohnq.propertiesrentalapp.view.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.joohnq.propertiesrentalapp.R
import com.joohnq.propertiesrentalapp.view.theme.GrayFCFCFC

@Composable
fun PermissionDialog(
    permission: String,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
) {
    AlertDialog(
        containerColor = GrayFCFCFC,
        onDismissRequest = onDismiss,
        tonalElevation = 0.dp,
        shape = RoundedCornerShape(16.dp),
        confirmButton = {
            OutlineButtonLargeSquare(text = stringResource(id = R.string.confirm)) {
                if (isPermanentlyDeclined) {
                    onGoToAppSettingsClick()
                } else {
                    onOkClick()
                }
                onDismiss()
            }
        },
        title = { Text(stringResource(id = R.string.permission_required), style = h1_24_bold_fs) },
        text = {
            Text(
                stringResource(id = R.string.permission_to_access, permission),
                style = p_14_normal_inter
            )
        },
    )
}