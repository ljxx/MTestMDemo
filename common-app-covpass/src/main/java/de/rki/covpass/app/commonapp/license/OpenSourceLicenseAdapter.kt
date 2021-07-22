/*
 * (C) Copyright IBM Deutschland GmbH 2021
 * (C) Copyright IBM Corp. 2021
 */

package de.rki.covpass.app.commonapp.license

import android.annotation.SuppressLint
import android.text.method.LinkMovementMethod
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import de.rki.covpass.app.commonapp.license.models.OpenSourceItem
import de.rki.covpass.app.commonapp.utils.stripUnderlines
import de.rki.covpass.app.databinding.OpenSourceItemBinding
import de.rki.covpass.sdk.utilslib.BaseRecyclerViewAdapter
import de.rki.covpass.sdk.utilslib.BindingViewHolder
import de.rki.covpass.sdk.utilslib.getSpanned

/**
 * [BaseRecyclerViewAdapter] which holds a list of [OpenSourceItem]
 */
public class OpenSourceLicenseAdapter(parent: Fragment) :
    BaseRecyclerViewAdapter<OpenSourceLicenseAdapter.OpenSourceLicenceViewHolder>(parent) {

    private var openSourceItems: List<OpenSourceItem> = emptyList()

    public fun updateList(newOpenSourceItems: List<OpenSourceItem>) {
        openSourceItems = newOpenSourceItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenSourceLicenceViewHolder =
        OpenSourceLicenceViewHolder(parent)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OpenSourceLicenceViewHolder, position: Int) {
        val item = openSourceItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = openSourceItems.size

    public class OpenSourceLicenceViewHolder(parent: ViewGroup) :
        BindingViewHolder<OpenSourceItemBinding>(
            parent,
            OpenSourceItemBinding::inflate
        ) {
        @SuppressLint("SetTextI18n")
        public fun bind(item: OpenSourceItem) {
            with(binding) {
                if (!item.project.isNullOrEmpty() && !item.version.isNullOrEmpty()) {
                    title.text = "${item.project} - ${item.version}"
                } else {
                    title.isVisible = false
                }
                val license = item.licenses?.firstOrNull()
                if (license != null) {
                    copyright.apply {
                        text = getSpanned("${license.license} (#${license.licenseUrl}::${license.licenseUrl}#)")
                        movementMethod = LinkMovementMethod.getInstance()
                        stripUnderlines()
                    }
                } else {
                    copyright.isVisible = false
                }
                if (!item.description.isNullOrEmpty()) {
                    description.text = item.description
                } else {
                    description.isVisible = false
                }
            }
        }
    }
}