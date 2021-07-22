/*
 * (C) Copyright IBM Deutschland GmbH 2021
 * (C) Copyright IBM Corp. 2021
 */

package de.rki.covpass.app.onboarding

import de.rki.covpass.app.commonapp.dependencies.commonDeps
import de.rki.covpass.app.commonapp.onboarding.BaseOnboardingContainerFragment
import de.rki.covpass.app.commonapp.storage.OnboardingRepository.Companion.CURRENT_DATA_PRIVACY_VERSION
import de.rki.covpass.app.commonapp.utils.SimpleFragmentStateAdapter
import de.rki.covpass.app.main.MainFragmentNav
import de.rki.covpass.sdk.navigation.FragmentNav
import de.rki.covpass.sdk.navigation.findNavigator
import kotlinx.parcelize.Parcelize

@Parcelize
internal class OnboardingContainerFragmentNav : FragmentNav(OnboardingContainerFragment::class)

/**
 * Fragment which holds the [SimpleFragmentStateAdapter] with Covpass specific Onboarding steps
 */
internal class OnboardingContainerFragment : BaseOnboardingContainerFragment() {

    override val fragmentStateAdapter by lazy {
        SimpleFragmentStateAdapter(
            parent = this,
            fragments = listOf(
                OnboardingInfo1Fragment(),
                OnboardingInfo2Fragment(),
                OnboardingInfo3Fragment(),
                OnboardingConsentFragment(),
            ),
        )
    }

    override fun finishOnboarding() {
        launchWhenStarted {
            commonDeps.onboardingRepository.dataPrivacyVersionAccepted
                .set(CURRENT_DATA_PRIVACY_VERSION)
            findNavigator().popAll()
            findNavigator().push(MainFragmentNav(), true)
        }
    }
}
