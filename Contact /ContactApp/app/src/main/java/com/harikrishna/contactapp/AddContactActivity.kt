package com.harikrishna.contactapp

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toIcon
import com.harikrishna.contactapp.model.Contact


class AddContactActivity : AppCompatActivity() {
    private lateinit var contactImage:ImageView
    private lateinit var imageBitmap:Icon
    private lateinit var imageBitmapString: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        contactImage= findViewById<ImageView>(R.id.conatct_image)
        contactImage.setOnClickListener {
            Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("select option")
            builder.setMessage("Choose your image")
            //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

//            builder.setPositiveButton("Take Photo") { dialog, which ->
//                val takePicture = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
//                startActivityForResult(takePicture, 0)
//            }

            builder.setNegativeButton("Choose from Gallery") { dialog, which ->
                val pickPhoto = Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(pickPhoto, 1)
            }

            builder.setNeutralButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            builder.create()
            builder.show()
        }




    }
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (resultCode != Activity.RESULT_CANCELED) {
            when (requestCode) {
                0 -> if (resultCode == Activity.RESULT_OK && data != null) {
                    val selectedImage = data.extras["data"] as Bitmap
                    contactImage.setImageBitmap(selectedImage)
                }
                1 -> if (resultCode == Activity.RESULT_OK && data != null) {
                    val selectedImage: Uri? = data.data

                    val filePathColumn =
                        arrayOf(MediaStore.Images.Media.DATA)
                    if (selectedImage != null) {
                        val cursor: Cursor? = contentResolver.query(
                            selectedImage,
                            filePathColumn, null, null, null
                        )
                        if (cursor != null) {
                            cursor.moveToFirst()
                            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                            val picturePath: String = cursor.getString(columnIndex)
                            contactImage.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                            imageBitmap=selectedImage.toIcon()
                            imageBitmapString=imageBitmap.toString()
                            Toast.makeText(this,imageBitmapString,Toast.LENGTH_LONG).show()
                            contactImage.setImageIcon(imageBitmap)
                            cursor.close()
                        }
                    }
                }
            }
        }
    }

    fun navSaveClicked(view: View) {
val fname=findViewById<EditText>(R.id.fname)
        val lname=findViewById<EditText>(R.id.lname)
        val email=findViewById<EditText>(R.id.email)
        val phoneno=findViewById<EditText>(R.id.phoneno)
        val contact=Contact(fname = fname.text.toString(),lname = lname.text.toString(),email = email.text.toString(),phoneno = phoneno.text.toString(),image = imageBitmap.toString())
        Toast.makeText(this,contact.toString(),Toast.LENGTH_LONG).show()
    }

}
