package uk.me.karlsen.ode;

import uk.me.karlsen.ode.stores.BaseItemStore;
import uk.me.karlsen.ode.stores.BaseMonsterStore;
import uk.me.karlsen.ode.stores.CharacterStore;
import uk.me.karlsen.ode.stores.ItemModifiersStore;
import uk.me.karlsen.ode.stores.QuestStore;
import uk.me.karlsen.ode.stores.ShrinesStore;
import uk.me.karlsen.ode.stores.SpellsStore;
import uk.me.karlsen.ode.stores.UniqueItemStore;
import uk.me.karlsen.ode.stores.UniqueMonsterStore;

public class OpenDiabloEditor {

	private ReaderWriter rw;

	QuestStore questStore;
	SpellsStore spellStore;
	ShrinesStore shrineStore;
	ItemModifiersStore modifierStore;
	UniqueItemStore uniqueItemStore;
	CharacterStore characterStore;
	BaseItemStore baseItemStore;
	BaseMonsterStore baseMonsterStore;
	UniqueMonsterStore uniqueMonsterStore;

	public static void main(String[] args){
		OpenDiabloEditor dm = new OpenDiabloEditor();
		dm.run();
	}

	public void run(){

		//####################################
		//#      HERE WE READ IN DATA        #
		//####################################

		rw = new ReaderWriter(false);

		questStore = new QuestStore(rw);
		spellStore = new SpellsStore(rw);
		shrineStore = new ShrinesStore(rw);
		modifierStore = new ItemModifiersStore(rw);
		uniqueItemStore = new UniqueItemStore(rw);
		characterStore = new CharacterStore(rw);
		baseItemStore = new BaseItemStore(rw);
		baseMonsterStore = new BaseMonsterStore(rw);
		uniqueMonsterStore = new UniqueMonsterStore(rw);


		//####################################
		//# HERE WE SHOW YOU STUFF TO CHANGE #
		//####################################


		//Uncomment the ones you want to see when running the program...

		//questStore.printQuests();
		//spellStore.printSpells();
		//shrineStore.printShrines();
		//modifierStore.printModifiers();
		//uniqueItemStore.printItems();
		//characterStore.printCharacters();
		//baseItemStore.printItems();
		//baseMonsterStore.printMonsters();
		//uniqueMonsterStore.printUniques();


		//######################################################
		//# DO YOUR MODDING STUFF BELOW HERE -- EXAMPLES BELOW #
		//######################################################

		//v1
		//shrineStore.disableBadShrines();

		//v2
		//characterStore.setAllMaxStatsTo255();

		//v3
		//characterStore.setCharZeroStartingSkillBySpellID(2); //healing
		//characterStore.setCharOneStartingSkillBySpellID(9); //infravision (inner sight)
		//characterStore.setCharTwoStartingSkillBySpellID(5); //identify

		//v4 -- TODO
		//fix or disable yellow zombies
		//change name of infravision
		//make healing skill mana drain

		//####################################
		//# NOW WE WRITE TO DIABLO.EXE COPY  #
		//####################################

		writeAllData();
	}

	private void writeAllData() {
		shrineStore.writeShrinesToEXE();
		questStore.writeQuestsToEXE();
		spellStore.writeSpellsToEXE();
		modifierStore.writeModifiersToEXE();
		uniqueItemStore.writeItemsToEXE();
		characterStore.writeCharactersToEXE();
		baseItemStore.writeItemsToEXE();
		baseMonsterStore.writeMonstersToEXE();
		uniqueMonsterStore.writeMonstersToEXE();
	}
}
