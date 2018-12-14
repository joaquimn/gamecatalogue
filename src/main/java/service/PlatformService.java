package service;

import domain.Platform;
import repository.PlatformRepository;

import java.util.ArrayList;
import java.util.List;

public class PlatformService {
    int platformId;
    String namePlatform;
    String createdAt;

    static PlatformRepository platformRepository = new PlatformRepository();

    public List<Platform> findPlatformsByUser( String userId ){

        List<Platform> platforms = new ArrayList<Platform>();

        platforms = platformRepository.findPlatformsByUser(userId);

        return platforms;
    }
}


