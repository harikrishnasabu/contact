package com.pms.drugx.api



import com.harikrishna.contactapp.Contact
import retrofit2.http.*


interface ApiService {

    @GET("/contacts")
     fun getContacts(

    ): Contact

}