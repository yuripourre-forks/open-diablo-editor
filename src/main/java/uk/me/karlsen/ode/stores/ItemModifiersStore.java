package uk.me.karlsen.ode.stores;

import java.util.ArrayList;
import java.util.List;

import uk.me.karlsen.ode.ReaderWriter;
import uk.me.karlsen.ode.TomeOfKnowledge;
import uk.me.karlsen.ode.types.ItemModifier;

public class ItemModifiersStore {

	ReaderWriter rw = null;
	List<ItemModifier> itemModifiers = null;

	public ItemModifiersStore(ReaderWriter rw){
		this.rw = rw;
		itemModifiers = new ArrayList<ItemModifier>();
		this.readInModifiers();
	}

	public void readInModifiers(){
		long pos = TomeOfKnowledge.MODIFIERS_OFFSET;
		long spacing = TomeOfKnowledge.MODIFIER_LENGTH_IN_BYTES;
		for(int i = 0; i < TomeOfKnowledge.NUMBER_OF_MODIFIERS; i++){
			readModifier(pos);
			pos = pos + spacing;
		}
	}

	private void readModifier(long position) {
		long pos = position;
		rw.seek(pos);
		byte[] readIn = new byte[TomeOfKnowledge.MODIFIER_LENGTH_IN_BYTES];
		for(int j = 0; j < TomeOfKnowledge.MODIFIER_LENGTH_IN_BYTES; j++){
			readIn[j] = rw.readByte();
			pos++;
			rw.seek(pos);
		}

		ItemModifier im = new ItemModifier(readIn, rw);
		itemModifiers.add(im);
	}

	public void printModifiers(){
		for(ItemModifier im : itemModifiers){
			im.printModifier();
		}
	}

	public byte[] getModifierAsBytes(int i) {
		return itemModifiers.get(i).getModifierAsBytes();
	}

	public void writeModifiersToEXE() {
		long pos = TomeOfKnowledge.MODIFIERS_OFFSET;
		for(int i = 0; i < TomeOfKnowledge.NUMBER_OF_MODIFIERS; i++){
			byte[] modifierAsBytes = this.getModifierAsBytes(i);
			rw.writeBytes(modifierAsBytes, pos);
			pos = pos + TomeOfKnowledge.MODIFIER_LENGTH_IN_BYTES;
		}
	}

	public String[] getModifierNames() {
		String[] modifierNames = new String[itemModifiers.size()];
		for(int i = 0; i < itemModifiers.size(); i++){
			ItemModifier iMod = itemModifiers.get(i);
			modifierNames[i] = iMod.getName();
		}
		return modifierNames;
	}

	public ItemModifier getModifierByName(String modifierName) {
		ItemModifier modifierToReturn = null;
		for(ItemModifier m : itemModifiers)
		{
			if(m.getName().equals(modifierName))
			{
				modifierToReturn = m;
				break;
			}
		}
		return modifierToReturn;
	}
}
