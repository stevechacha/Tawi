package com.freshtawi.tawi.presentation.screen.farm.farm_overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.freshtawi.tawi.presentation.common.resources.Resources

@Composable
@Preview
fun RecordScreen() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Record")
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Filter(100 +",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "EDIT",
                fontWeight = FontWeight.Bold

            )

        }
        FarmNoteCard(
            noteDate = "23/02/2023",
            noteDescription = Resources.strings.description
//            noteDescription = stringResource(id = R.string.farm_note_desc)
        )
        FarmNoteCard(
            noteDate = "24/05/2023",
            noteDescription = Resources.strings.description
//            noteDescription = stringResource(id = R.string.farm_note_desc)
        )
    }
}

@Composable
fun FarmNoteCard(
    modifier: Modifier = Modifier,
    noteDate: String,
    noteDescription: String
) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
    ) {
      Column(
          modifier = Modifier.fillMaxWidth().padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
          Row (
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
          ){
              Text(
                  text = "Note to self:",
                  textAlign = TextAlign.Left,
                  fontWeight = FontWeight.SemiBold
              )
              Text(
                  text = noteDate,
                  textAlign = TextAlign.Right
              )
          }
      }

      Text(
          text = noteDescription,
          modifier = Modifier.fillMaxWidth()
      )
    }

}
