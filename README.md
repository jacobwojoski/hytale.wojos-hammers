# Wojos Hammer
Make a mining hammer that breaks blocks in a 3x3 from the selected item.

## Animation Guide
The item that triggers the animation creates an interaction chain. A simple chain is as follows
- `My_Tool.json:: Interactions: { Primary: { My_Tool_Selector.json }` The tool goes to a selector json it pick what the user is hitting
- `My_Tool_Selector.json: { Effect: { PlayerAnimationId: SwingLeft, <Some selector info> }}` Tell the selector what animation to play and some details about how to select what your animation interacts with.
- `SwingLeft` Is an key for a key value Animation stored in the `Server/Item/Animations/My_Tool.json` file.



# Hytale Example Plugin

An example project that can build and run plugins for the game Hytale!

> **⚠️ Warning: Early Access**    
> The game Hytale is in early access, and so is this project! Features may be
> incomplete, unstable, or change frequently. Please be patient and understanding as development
> continues.

## Introduction
This project contains a Gradle project that can be imported into IDEA and used
as the foundation for custom Hytale plugins. The template will add the Hytale
server to your classpath and create a run configuration that can be used to
run your plugin on the server. It can also be used to build a sharable JAR file
that contains your plugin.

## Requirements
Please ensure all the requirements are met before getting started.

1. Download Hytale using the official launcher.
2. Have Intellij IDEA installed. Community edition is fine.
3. Download Java 25 and set it as the SDK in IDEA.

Currently this template only supports Windows!

## Configuring Template
It is important to configure the project before using it as a template. Doing
this before importing the project will help avoid running into caching issues
later on.

### 1: Project Name
Set the name of the project in `settings.gradle`. This should be the name of
your plugin. We recommend capitalizing your project name and avoiding 
whitespace and most special characters. This will be used as the base name for
any files produced by Gradle, like the sharable JAR file.

### 2: Gradle Properties
Review the properties defined in `gradle.properties`. You should change the 
`maven_group` to match your project. You should also change the `version`
property before making a new release, or set up CI/CD to automate it.

### 3: Manifest
The manifest file provides important information about your plugin to Hytale.
You should update every property in this file to reflect your project. The 
most important property to set is `Main` which tells the game which class
file to load as the entry point for your plugin. The file can be found at 
`src/main/resources/manifest.json`.

**This template has configured Gradle to automatically update the `Version` and
`IncludesAssetPack` property to reflect your Gradle properties every time you 
run the game in development, or build the plugin. This is a workaround to allow
the in-game asset editor to be used when working on your project.**

## Importing into IDEA
When opening the project in IDEA it should automatically create the
`HytaleServer` run configuration and a `./run` folder. When you run the game it
will generate all the relevant files in there. It will also load the default 
assets from the games.

**If you do not see the `HytaleServer` run configuration, you may need to open
the dropdown or click `Edit Configurations...` once to unhide it.**

## Connecting to Server
Once the server is running in IDEA you should be able to connect to 
`Local Server` using your standard Hytale client. If the server does not show
up automatically, add the IP as `127.0.0.1` manually.

### You MUST authenticate your test server!
In order to connect to the test server, you must authenticate it with Hytale.
This is done by running the `auth login device` command in the server terminal.
This command will print a URL that you can use to authenticate the server using
your Hytale account. Once authenticated, you can run the 
`auth persistence Encrypted` command to keep your server authenticated after 
restarting it. 

**Never share your encrypted auth file!**

If you are unable to run commands from the IDEA terminal, you can also run the 
command from code like this. Make sure to remove the code after your server is
authenticated.

```java
    @Override
    protected void start() {
        CommandManager.get().handleCommand(ConsoleSender.INSTANCE, "auth login device");
    }
```


## Verifying The Example Plugin
You can verify the Example plugin has loaded by running the `/test` command 
in game. It will print the name and version of your plugin. This is for 
demonstration purposes, and should **NOT** be included in your final build.

The example plugin also includes a recipe defined by an asset pack. This recipe
allows you to craft 10 dirt into 1 dirt using the crafting window. This is also
an example and should not be removed before you release the plugin.

## Development Planns
- Hammer
			- Primary
				- Light
					- 1x3x1: Horizontal(Break 3 Blocks total)
					- (Med Knockback & Minimal dmg vs Hit Monsters)
				- Heavy
					- 3x3x1 (Break 9 Blocks)
					- (Heavy Knockback & Minimal dmg vs Hit Monsters)
					- Overhead Smash
			- Secondary
				- Light
					- 3x1x1: Vertical (Break 3 Blocks total)
				- Heavy
					- Break Single Block (Doesn't drop item? Slow? Tier Down Item?)
					- (Heavy Knockback & Minimal Damage vs Single Monster)
			- Special
				- 3x3x3 (Break 27 Blocks)
				- Charging
					- Bonus charge when hitting same block type
					- Charge decays over time when not breaking blocks
					- Full charge can decay out
					- Bonus charge when block gets broken
			- Components
				- HammerCharge (Can get added and removed from player)
					- CurrentChargeValue			0			// Current charge value
					- MaxTotalChargeValue			150			// Max total carge value
					- SpecialReadyChargeThreshold	100			// Charge Required for Special to be ready
					- ChargeDeayDelaySeconds		10 Seconds	// Time Delay before charge starts to decau
					- ChargeDelayCountdown			10 Seconds	// Delay tracking counter 
					- ChargeDecayIntervalSeconds	1 Second	// How often does decay get triggered
					- ChargeDecayValue				5			// How much carge decayed per tick
					- ChargePerHit					1			// How much charge gained when hitting a block
					- ChargePerBlockBreak			5			// How much charge gained when breaking a block
					- BlockTypeBroken				BlockId		// What was the block type that was broken
					- NumTimesBlockTypeBroken		0			// How many times have we broken the same block type
					- BonusPerNumBlockBroken		1			// Charge Value * NumTimesBlockTypeBroken as stacking bonus when breaking a block
				- BreakBlockEvent (Filter entity w/ HammerCharge Component)
				- HammerChargeSystem (Decays Hammer Change by X Ammount every Y Server Tic's, Remove component at 0)
			- Misc:
				- Short Range
				- Target Block Ghos
