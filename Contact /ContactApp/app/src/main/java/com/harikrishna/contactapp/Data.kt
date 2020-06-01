package com.harikrishna.contactapp

import android.widget.Toast
import com.harikrishna.contactapp.model.Contact
import com.pms.drugx.api.ApiService

class Data {
    companion object {
        fun createDataSet(): ArrayList<Contact> {
            val list = ArrayList<Contact>()

            list.add(
                Contact(
                    "harry!",
                    "Sabz",
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png",
                    "harry@gmail.com",
                    phoneno = "1234567"
                )
            )
            list.add(
                Contact(
                    "harry!",
                    "Sabz",
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png",
                    "harry@gmail.com",
                    phoneno = "1234567"
                )
            )
            list.add(
                Contact(
                    "harry!",
                    "Sabz",
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png",
                    "harry@gmail.com",
                    phoneno = "1234567"
                )
            )
            list.add(
                Contact(
                    "harry!",
                    "Sabz",
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png",
                    "harry@gmail.com",
                    phoneno = "1234567"
                )
            )
            return list
        }


        fun getContacts(): ArrayList<DbData> {


            val response=  MyRetrofitBuilder.apiService.getContacts().db_data

            return response
        }
    }
}