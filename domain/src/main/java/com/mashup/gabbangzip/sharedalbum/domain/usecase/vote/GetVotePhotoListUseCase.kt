package com.mashup.gabbangzip.sharedalbum.domain.usecase.vote

import com.mashup.gabbangzip.sharedalbum.domain.model.vote.VotePhotoDomainModel
import com.mashup.gabbangzip.sharedalbum.domain.repository.VoteRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetVotePhotoListUseCase @Inject constructor(
    private val voteRepository: VoteRepository,
) {
    suspend operator fun invoke(eventId: Long): Result<List<VotePhotoDomainModel>> {
        return runCatching {
            voteRepository.getVotePhotoList(eventId)
        }
    }
}
