package som.sps.zmoto.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModelFactory(
    private val entityId: Int,
    private val entityType: String,
    private val categoryId: Int
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (PageViewModel::class.java.isAssignableFrom(modelClass)) {
            return PageViewModel(entityId, entityType, categoryId) as T
        }
        return super.create(modelClass)
    }
}