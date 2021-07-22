/*
 * (C) Copyright IBM Deutschland GmbH 2021
 * (C) Copyright IBM Corp. 2021
 */

package de.rki.covpass.app.information

import de.rki.covpass.app.R
import de.rki.covpass.app.commonapp.information.InformationFragment
import de.rki.covpass.sdk.navigation.FragmentNav
import kotlinx.parcelize.Parcelize

@Parcelize
internal class CovPassInformationFragmentNav : FragmentNav(CovPassInformationFragment::class)

/**
 * Covpass specific Information screen. Overrides the abstract functions from [InformationFragment].
 */
internal class CovPassInformationFragment : InformationFragment() {

    override fun getFAQLinkRes() = R.string.information_faq_link

    override fun getImprintLinkRes() = R.string.information_imprint_link
}
