package com.example.pam_20nov.ui.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.provider.FontsContractCompat.Columns
import com.example.pam_20nov.R
import com.example.pam_20nov.data.MataKuliah
import com.example.pam_20nov.data.RuangKelas
import com.example.pam_20nov.model.Mahasiswa
import com.example.pam_20nov.widget.DynamicSelectedField

@Composable
fun RencanaStudyView(
    mahasiswa: Mahasiswa,
    onbackbuttonClicked: () -> Unit,
){
    var chosenDropdown by remember {
        mutableStateOf(
            ""
        )
    }

    var checked by remember { mutableStateOf(false) }
    var pilihanKelas by remember {
        mutableStateOf("")
    }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary))
    ){
        Row (modifier = Modifier
            .padding(40.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                painter = painterResource(
                    id = R.drawable.umy
                ),
                contentDescription = ""
            )

            Spacer(modifier = Modifier.padding(start = 30.dp))

            Column {
                Text(
                    text = mahasiswa.nama,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = mahasiswa.nim,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Box{
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = ""
                )
            }
        }

        Box(modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topEnd = 15.dp,
                    topStart = 15.dp
                )
            )
            .fillMaxWidth()
        ){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
            ){
                Text(
                    text = "Pilih Mata Kuliah Peminatan",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Silahkan pilih kelas dari mata kuliah yang anda inginkan",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )

                Spacer(modifier = Modifier.padding(8.dp))

                DynamicSelectedField(
                    selectedValue = chosenDropdown,
                    options = MataKuliah.options,
                    label = "Mata Kuliah",
                    onValueChangedEvent = {
                        chosenDropdown = it
                    }
                )

                Spacer(modifier = Modifier.padding(8.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.padding(8.dp))


                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    RuangKelas.kelas.forEach { data ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = pilihanKelas == data,
                                onClick = { pilihanKelas = data }
                            )
                        }
                    }

                    Button(onClick = {onbackbuttonClicked()}) {
                        Text(text = "Kembali")
                    }

                }
            }
        }
    }
}