object Modules {
    const val APP = ":app"

    object AndroidLibrary {
        const val CORE = ":libraries:core"
        const val DATA = ":libraries:data"
        const val DOMAIN = ":libraries:domain"
        const val TEST_UTILS = ":libraries:test-util"
    }


    /**
     * Dynamic Feature Modules
     */
    object DynamicFeature {
        const val HOME = ":features:home"
        const val MOVIE_DETAILS = ":features:movieDetails"
        const val SHOWS = ":features:shows"
      /*
        const val SHOWS_DETAILS = ":features:showsDetails"*/
    }
}