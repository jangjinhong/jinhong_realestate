package com.example.jinhong_realestate.service;

import com.example.jinhong_realestate.entity.Agent;
import com.example.jinhong_realestate.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentService {

    private final AgentRepository agentRepository;

    public  Agent saveAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    /**
     * 주어진 ID에 해당하는 대리인 정보를 반환합니다.
     *
     * @param agent_id 찾을 대리인 정보의 ID
     * @return 주어진 ID에 해당하는 대리인 정보
     */
    public  Agent findAgentById(Long agent_id) {
        return agentRepository.findById(agent_id).orElse(null);
    }

    public  List<Agent> findAllAgents() {
        return agentRepository.findAll();
    }

    /**
     * 주어진 ID에 해당하는 대리인 정보를 상세하게 반환합니다.
     * 트랜잭션과 렌탈 정보를 포함합니다.
     *
     * @param agentId 찾을 대리인 정보의 ID
     * @return 상세한 대리인 정보
     */
    public Agent getAgentByIdWithDetails(Long agentId) {
        Agent agent = agentRepository.findById(agentId).orElse(null);
        if (agent != null) {
            agent.getTransactions().size(); // 트랜잭션 초기화
            agent.getRentals().size(); // 렌탈 초기화
        }
        return agent;
    }
    public  void deleteAgent(Long agent_id) {
        agentRepository.deleteById(agent_id);
    }
}