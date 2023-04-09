package persistence.interfaces;

import persistence.DTO.ProfileDto;

import java.util.function.Supplier;

/**
 * Method definitions concerning profile repo usage
 */
public interface ProfileInterface {

    /**
     * Read data from source
     * @return profileData
     */
    Supplier<ProfileDto> readFromProfile();


    /**
     * Write profile data to destination
     * @param dto - holding profile data
     * @return true / false on IO
     */
    boolean writeToProfile(ProfileDto dto);

}
