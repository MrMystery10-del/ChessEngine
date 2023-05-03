package persistence.interfaces;

import persistence.DTO.ProfileDto;

/**
 * Method definitions concerning profile repo usage
 */
public interface ProfileInterface {

    /**
     * Read data from source
     *
     * @return ProfileDto
     */
    ProfileDto readFromProfile();


    /**
     * Write profile data to destination
     *
     * @param dto - holding profile data
     * @return true / false on IO
     */
    boolean writeToProfile(ProfileDto dto);
}