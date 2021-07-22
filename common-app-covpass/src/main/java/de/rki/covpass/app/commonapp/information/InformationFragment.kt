/*
 * (C) Copyright IBM Deutschland GmbH 2021
 * (C) Copyright IBM Corp. 2021
 */

package de.rki.covpass.app.commonapp.information

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import de.rki.covpass.app.R
import de.rki.covpass.app.commonapp.BaseFragment
import de.rki.covpass.app.commonapp.OpenSourceLicenseFragmentNav
import de.rki.covpass.app.commonapp.onboarding.DataProtectionFragmentNav
import de.rki.covpass.app.commonapp.utils.stripUnderlines
import de.rki.covpass.app.databinding.InformationBinding
import de.rki.covpass.sdk.navigation.findNavigator
import de.rki.covpass.sdk.utilslib.appVersion
import de.rki.covpass.sdk.utilslib.attachToolbar
import de.rki.covpass.sdk.utilslib.getSpanned
import de.rki.covpass.sdk.utilslib.viewBinding

/**
 * Common base fragment to display the faq, imprint etc. Both apps use the same fragment, only the different links are
 * defined inside the app-specific fragments.
 */
public abstract class InformationFragment : BaseFragment() {

    private val binding by viewBinding(InformationBinding::inflate)

    @SuppressLint("StringFormatInvalid")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        binding.informationAppVersionLabel.text = getString(R.string.app_information_version_label, appVersion)
        binding.informationFieldFaq.apply {
            text = getSpanned(
                R.string.app_information_title_faq_linked,
                getString(getFAQLinkRes())
            )
            movementMethod = LinkMovementMethod.getInstance()
            stripUnderlines()
        }
        binding.informationFieldDataSecurityPolicy.apply {
            text = getString(R.string.app_information_title_datenschutz)
            setOnClickListener {
                findNavigator().push(DataProtectionFragmentNav())
            }
        }
        binding.informationFieldImprint.apply {
            text = getSpanned(
                R.string.app_information_title_company_details_linked,
                getString(getImprintLinkRes())
            )
            movementMethod = LinkMovementMethod.getInstance()
            stripUnderlines()
        }

        binding.informationFieldOpenSourceLicenses.apply {
            text = getString(R.string.app_information_title_open_source)
            setOnClickListener {
                findNavigator().push(OpenSourceLicenseFragmentNav())
            }
        }

        binding.informationFieldContacts.apply {
            setText(R.string.app_information_title_contact)
            setOnClickListener {
                findNavigator().push(ContactsFragmentNav())
            }
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
            binding.informationToolbar.setTitle(R.string.app_information_title)
        }
    }

    protected abstract fun getFAQLinkRes(): Int
    protected abstract fun getImprintLinkRes(): Int
}
