package de.rki.covpass.app.commonapp.information

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import de.rki.covpass.app.R
import de.rki.covpass.app.commonapp.BaseFragment
import de.rki.covpass.app.commonapp.utils.stripUnderlines
import de.rki.covpass.app.databinding.ContactsBinding
import de.rki.covpass.sdk.navigation.FragmentNav
import de.rki.covpass.sdk.utilslib.attachToolbar
import de.rki.covpass.sdk.utilslib.getSpanned
import de.rki.covpass.sdk.utilslib.viewBinding
import kotlinx.parcelize.Parcelize

@Parcelize
public class ContactsFragmentNav : FragmentNav(ContactsFragment::class)

/**
 * Displays the contacts
 */
public class ContactsFragment : BaseFragment() {

    private val binding by viewBinding(ContactsBinding::inflate)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        binding.titleTechnicalContacts.setText(R.string.app_information_message_contact_support)
        binding.textTechnicalEmail.apply {
            text = getSpanned(R.string.app_information_message_contact_support_mail)
            movementMethod = LinkMovementMethod.getInstance()
            stripUnderlines()
        }
        binding.textTechnicalPhone.apply {
            text = getSpanned(R.string.app_information_message_contact_support_phone)
            movementMethod = LinkMovementMethod.getInstance()
            stripUnderlines()
        }
        binding.titleGeneralContacts.setText(R.string.app_information_message_contact_info)
        binding.textGeneralEmail.apply {
            text = getSpanned(R.string.app_information_message_contact_info_mail)
            movementMethod = LinkMovementMethod.getInstance()
            stripUnderlines()
        }
    }

    private fun setupActionBar() {
        attachToolbar(binding.informationToolbar)
        (activity as? AppCompatActivity)?.run {
            supportActionBar?.run {
                setDisplayShowTitleEnabled(false)
                setDisplayHomeAsUpEnabled(true)
                setHomeAsUpIndicator(R.drawable.back_arrow)
            }
            binding.informationToolbar.setTitle(R.string.app_information_title_contact)
        }
    }
}
