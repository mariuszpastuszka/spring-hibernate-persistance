
@org.hibernate.annotations.FetchProfiles({
    /* 
        Each profile has a name, this is a simple string we have isolated in a constant.
     */
        @FetchProfile(name = Item.PROFILE_JOIN_SELLER,
                /*
                     Each override in a profile names one entity association or collection.
                 */
                fetchOverrides = @FetchProfile.FetchOverride(
                        entity = Item.class,
                        association = "seller",
                        mode = FetchMode.JOIN
                )),

        @FetchProfile(name = Item.PROFILE_JOIN_BIDS,
                fetchOverrides = @FetchProfile.FetchOverride(
                        entity = Item.class,
                        association = "bids",
                        mode = FetchMode.JOIN
                ))
})

package com.javapersistence.part12.profile;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;