import net.msrandom.minecraftcodev.runs.MinecraftRunConfiguration

plugins {
	id("earth.terrarium.cloche") version "0.17.7"
}

repositories {
	cloche.librariesMinecraft()

	mavenCentral()

	cloche {
		main()

		mavenFabric()
		mavenNeoforgedMeta()
		mavenNeoforged()

		mavenParchment()
	}
}

cloche {
	minecraftVersion = "1.21.1"

	metadata {
		modId = "stay_a_while"
		name = "Stay A While"
		license = "GPL-3.0"
		description = "Customize item despawn times"
		icon = "assets/stay_a_while/icon.png"

		author("jolkert")
	}

	mappings {
		official()
		parchment("2024.11.17")
	}

	dependencies {
	}

	val mixinExtrasVersion = "0.5.3"
	val mixinExtrasPath = "io.github.llamalad7:mixinextras"

	neoforge {
		loaderVersion = "21.1.135"
		metadata {
			mixins.from("src/common/stayawhile.mixins.json")
			mixins.from("src/neoforge/stayawhile.neoforge.mixins.json")
		}

		data()

		dependencies {
			val mixinExtrasNeoforge = "$mixinExtrasPath-neoforge:$mixinExtrasVersion"

			include(mixinExtrasNeoforge)
			implementation(mixinExtrasNeoforge)
			annotationProcessor(mixinExtrasNeoforge)
		}

		runs {
			server {
				args("nogui")
			}
			client {
				setUsernameAndUuid()
			}
			data()
		}
	}

	fabric {
		loaderVersion = "0.16.10"
		val fabricApiVersion = "0.115.2"

		metadata {
			entrypoint("main", "dev.jolkert.stayawhile.fabric.StayAWhileFabric")
			mixins.from("src/common/stayawhile.mixins.json")
			mixins.from("src/fabric/stayawhile.fabric.mixins.json")

			dependency("minecraft", minecraftVersion.get())
			dependency {
				modId = "fabric"
			}
		}

		data()
		
		client {
			tasks.named<Jar>(sourceSet.jarTaskName) {
				duplicatesStrategy = DuplicatesStrategy.INCLUDE
			}
		}

		dependencies {
			fabricApi(fabricApiVersion)
			val mixinExtrasFabric = "$mixinExtrasPath-fabric:$mixinExtrasVersion"

			include(mixinExtrasFabric)
			implementation(mixinExtrasFabric)
			annotationProcessor(mixinExtrasFabric)
		}

		runs {
			server()
			client {
				setUsernameAndUuid()
			}
			data()
		}
	}
}

fun MinecraftRunConfiguration.setUsernameAndUuid()
{
	val username = System.getenv("MC_USER")
	if (!username.isNullOrBlank())
	{
		args("--username", username)
	}

	val uuid = System.getenv("MC_UUID")
	if (!uuid.isNullOrBlank())
	{
		args("--uuid", uuid)
	}
}
