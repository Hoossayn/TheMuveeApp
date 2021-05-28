include(":libraries:core")

include (
        ":app",
        ":libraries:test-utils",
        ":features:home",
        ":features:notification"

)
rootProject.name = "TheMuveeApp"
include(":libraries:data")
include(":libraries:domain")
