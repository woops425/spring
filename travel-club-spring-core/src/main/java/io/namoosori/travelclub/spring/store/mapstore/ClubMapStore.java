package io.namoosori.travelclub.spring.store.mapstore;

import io.namoosori.travelclub.spring.aggregate.club.TravelClub;
import io.namoosori.travelclub.spring.store.ClubStore;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("clubStore")
public class ClubMapStore implements ClubStore {

    private Map<String, TravelClub> clubMap;

    public ClubMapStore() {
        this.clubMap = new LinkedHashMap<>();
    }


    @Override
    public String create(TravelClub club) { // 파라미터로 새로 저장해야하는 TravelClub의 정보가 넘어옴
        clubMap.put(club.getId(), club);
        return club.getId();
    }

    @Override
    public TravelClub retrieve(String clubId) { // 하나의 클럽을 찾는 것. 파라미터로 Id가 넘어옴
        return clubMap.get(clubId);
    }

    @Override
    public List<TravelClub> retrieveByName(String name) {   // 파라미터로 클럽의 이름이 넘어옴(리스트 형태 - 동일한 이름 존재 가능, 동일한 이름도 모두 반환)

        return clubMap.values().stream()
                .filter( club -> club.getName().equals(name)) // 람다식 제공
                .collect(Collectors.toList());
    }

    @Override
    public void update(TravelClub club) {
        clubMap.put(club.getId(), club);
    }

    @Override
    public void delete(String clubId) {
        clubMap.remove(clubId);
    }

    @Override
    public boolean exists(String clubId) {
//        return clubMap.containsKey(clubId);
        return Optional.ofNullable(clubMap.get(clubId)).isPresent();
    }
}
