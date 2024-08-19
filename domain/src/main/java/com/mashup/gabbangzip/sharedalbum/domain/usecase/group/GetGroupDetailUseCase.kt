package com.mashup.gabbangzip.sharedalbum.domain.usecase.group

import com.mashup.gabbangzip.sharedalbum.domain.model.group.GroupDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.GroupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGroupDetailUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    operator fun invoke(groupId: Long): Flow<GroupDomainModel> {
        return flow {
            emit(groupRepository.getGroupDetail(groupId))
        }
    }
}
