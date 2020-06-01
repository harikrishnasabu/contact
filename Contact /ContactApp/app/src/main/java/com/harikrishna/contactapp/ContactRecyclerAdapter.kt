package com.harikrishna.contactapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.harikrishna.contactapp.model.Contact
import kotlinx.android.synthetic.main.layout_contact_list_item.view.*


class ContactRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    private val TAG: String = "AppDebug"

    private var items: ArrayList<Contact> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_contact_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is ContactViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(contactList: ArrayList<com.harikrishna.contactapp.model.Contact>){
        items = contactList
    }

    class ContactViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        val contact_image = itemView.conatct_image
        val conatct_name = itemView.name
        val contact_phoneno = itemView.phoneno
        val contact_email=itemView.email

        fun bind(contact: com.harikrishna.contactapp.model.Contact){

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(contact.image)
                .into(contact_image)
            conatct_name.setText(contact.fname+" "+contact.lname)
            contact_phoneno.setText(contact.phoneno)
            contact_email.setText(contact.email)

        }

    }

}
