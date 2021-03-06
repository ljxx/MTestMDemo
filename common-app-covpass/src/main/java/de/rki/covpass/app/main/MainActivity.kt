/*
 * (C) Copyright IBM Deutschland GmbH 2021
 * (C) Copyright IBM Corp. 2021
 */

package de.rki.covpass.app.main

import android.os.Bundle
import de.rki.covpass.app.commonapp.BaseActivity

/**
 * The only Activity in the app, hosts all fragments.
 * Initially triggers the navigation to [WelcomeFragmentNav] or [MainFragmentNav].
 */
internal class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (navigator.isEmpty() && savedInstanceState == null) {
//            if (commonDeps.onboardingRepository.dataPrivacyVersionAccepted.value >= CURRENT_DATA_PRIVACY_VERSION) {
                navigator.push(MainFragmentNav())
//            }
//        }
    }
}
