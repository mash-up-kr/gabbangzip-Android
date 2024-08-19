package com.mashup.gabbangzip.sharedalbum.domain.usecase.group

import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.GroupRepository
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class GetGroupListUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    operator fun invoke(): Flow<List<GroupDomainModel>> {
        return flow {
            emit(groupRepository.getGroupList())
        }
    }
}
