package som.sps.zmoto.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import som.sps.zmoto.model.CategoriesResponse
import som.sps.zmoto.ui.main.PlaceholderFragment


class SectionsPagerAdapter(fm: FragmentManager,private val items:CategoriesResponse) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return PlaceholderFragment.newInstance(items.categories[position].category.id)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return items.categories[position].category.name

    }

    override fun getCount(): Int {
        return items.categories.size
    }
}