package com.reloop.ecorewards.service;

import com.reloop.ecorewards.dto.request.RewardRequest;
import com.reloop.ecorewards.dto.response.PageResponse;
import com.reloop.ecorewards.dto.response.RewardResponse;
import com.reloop.ecorewards.entity.Reward;
import com.reloop.ecorewards.exception.ResourceNotFoundException;
import com.reloop.ecorewards.mapper.RewardMapper;
import com.reloop.ecorewards.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RewardService {

    private final RewardRepository rewardRepository;
    private final RewardMapper rewardMapper;

    @Transactional
    public RewardResponse create(RewardRequest request) {
        Reward saved = rewardRepository.save(rewardMapper.toEntity(request));
        return rewardMapper.toResponse(saved);
    }

    public RewardResponse getById(Long id) {
        return rewardMapper.toResponse(findOrThrow(id));
    }

    public PageResponse<RewardResponse> listAvailable(String category, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        Page<Reward> page = (category == null || category.isBlank())
                ? rewardRepository.findAvailable(now, pageable)
                : rewardRepository.findAvailableByCategory(category.trim(), now, pageable);
        return PageResponse.from(page, rewardMapper::toResponse);
    }

    @Transactional
    public RewardResponse update(Long id, RewardRequest request) {
        Reward reward = findOrThrow(id);
        rewardMapper.updateEntity(reward, request);
        return rewardMapper.toResponse(reward); // dirty checking saves it at commit
    }

    /** DELETE is a soft delete: past redemptions keep pointing to an existing reward. */
    @Transactional
    public void deactivate(Long id) {
        Reward reward = findOrThrow(id);
        rewardRepository.delete(reward);
    }

    private Reward findOrThrow(Long id) {
        return rewardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reward", id));
    }
}