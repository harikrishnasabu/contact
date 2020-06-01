package com.harikrishna.contactapp.model

data class Contact(

    var fname: String,

    var lname: String,

    var image: String,

    var email: String ,

    var phoneno:String


) {

    override fun toString(): String {
        return "Contact(fname='$fname', lname='$lname',image='$image',email='$email', phoneno='$phoneno')"
    }


}
