package com.emveyh.ld25;

public enum OverlordSpeeches {

	PRINCESS("the princess is mine!!"),
	KILL("kill these intruders!"),
	FOOL("you fools! she is mine!"),
	SURIVE("don't let anyone survive!"),
	DUNGEON("get out of my dungeon!"),
	DEATH("death upon all of you!"),
	TEAR("tear them apart!"),
	DIE("you are going to die"),
	VIL("i like being a villain. villains are more exciting"),
	VIL2("you know everyone loves to be the villain"),
	VIL3("i liked getting the best villain award"),
	VIL4("i'm the most evil villain of all time!"),
	VIL5("one murder made a villain, millions a hero"),
	VIL6("don't challange me!"),
	VIL7("run, you fools!"),
	DIE2("I expect you to die!"),
	JOHNNY("heeeeeeere's johnny!"),
	muha("muhahahahahahahaha!"),
	world("and soon the world will be mine!"),
	vil8("i'm villainous!"),
	allow("you exist because i allow it!"),
	end("you will end because i demand it!"),
	stand("don't just stand there! get them!"),
	mankind("i will rule over mankind!"),
	prep("prepare for unforseen consequences!"),
	die3("welcome ... to die!"),
	laugh("ha ha ha ha ha ... "),
	scum("prepare to die scum!"),
	end6("i will be your end!"),
	CAKE("we need more cake!"),
	MASTER("I'm Grakkor, King of the Goblings!"),
	FEAR("Fear the great Grakkor!"),
	FIGHT("You dare to fight Grakkor?!"),
	COME("Come at me bro!"),
	EAT("I will eat you alive!"),
	best("I'm the best"),
	master2("I'm Grakkor, Master of all villains!"),
	norris("I've beaten chuck norris once!"),
	bowser("bowser is nothing compared to me!"),
	ganon("ganon was my apprentice!"),
	isaac("i'm the father of isaac's mom!"),
	kitties("i hate kitties!"),
	goats("i hate goats!"),
	puppies("i hate puppies!"),
	christmas("i hate christmas more than the grinch!"),
	hate("i hate you all!"),
	please("please die ... there you go"),
	lawn("get off my lawn!"),
	dungeon2("that's my dungeon!"),
	shoes("you walk in my dungeon with your dirty shoes?!"),
	coconut("here they come, the knights of the coconut!"),
	pathetic("ha! pathetic!");
	
	
	private String text;
	
	private OverlordSpeeches(String text) {
		this.text = text.toUpperCase();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
