import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Readtxt {

	String steam_src="E:\\Games\\Steam";
	String vpk_src="E:\\dota2vpk\\root";
	String dota2_src="\\steamapps\\common\\dota 2 beta\\dota";
	String schinese_file="\\resource\\dota_schinese.txt";
	String items_file="\\scripts\\npc\\items.txt";
	String heroes_file="\\scripts\\npc\\npc_heroes.txt";
	String abilities_file="\\scripts\\npc\\npc_abilities.txt";
	File fs,fi,fh,fa;
	ArrayList<String> hero_string_list=new ArrayList<String>();
	ArrayList<String> ability_string_list=new ArrayList<String>();
	ArrayList<String> item_string_list=new ArrayList<String>();
	ArrayList<String> sc_string_list=new ArrayList<String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Readtxt rt=new Readtxt();
		rt.build();	
		
	}

	public void build(){
		fs=new File(steam_src+dota2_src+schinese_file);
		fi=new File(vpk_src+items_file);
		fh=new File(vpk_src+heroes_file);
		fa=new File(vpk_src+abilities_file);
		if (fs.exists() ) {
			try{
				BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(fs),"unicode"));
				String line=null;
				while ((line=reader.readLine())!=null){
					sc_string_list.add(line);
				}
				reader.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		} else {
			System.err.println("Can't find file!");
		}
		if (fi.exists() ) {
			try{
				BufferedReader reader=new BufferedReader(new FileReader(fi));
				String line=null;
				while ((line=reader.readLine())!=null){
					item_string_list.add(line);
				}
				reader.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		} else {
			System.err.println("Can't find file!");
		}
		if (fh.exists() ) {
			try{
				BufferedReader reader=new BufferedReader(new FileReader(fh));
				String line=null;
				while ((line=reader.readLine())!=null){
					hero_string_list.add(line);
				}
				reader.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		} else {
			System.err.println("Can't find file!");
		}
		if (fa.exists() ) {
			try{
				BufferedReader reader=new BufferedReader(new FileReader(fa));
				String line=null;
				while ((line=reader.readLine())!=null){
					ability_string_list.add(line);
				}
				reader.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		} else {
			System.err.println("Can't find file!");
		}
		
		//buildHero();
		//buildAbility();
		buildItem();
	}
	
	public void buildHero(){
		int i=0;
		Hero h=new Hero();
		boolean load_hero=false;
		WriteSQL.connect();
		while(i<hero_string_list.size()){
			//∆•≈‰v_name
			if(i==hero_string_list.size()-2 || (hero_string_list.get(i).contains("\"npc_dota_hero_") && !hero_string_list.get(i).contains("\"npc_dota_hero_base") && !hero_string_list.get(i).contains("LastHitChallengeRival") && !hero_string_list.get(i).contains("BaseClass"))){
				if(load_hero){
					load_hero=false;
					System.out.println("∂¡»°”¢–€"+h.v_name+"ÕÍ±œ...");
					//∞—ability∫Û√Êµƒ–°Œ≤∞Õ»•µÙ
					//System.out.println(h.v_name+"   "+h.ability_id.length());
					h.ability_id=h.ability_id.substring(0, h.ability_id.length()-1);
					//±£¥Ê“—∂¡»°µΩµƒ…œ“ª∏ˆ”¢–€–≈œ¢
					WriteSQL.updateHero(h);
					h=new Hero();
				}

				String mode = "\"npc_dota_hero_(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.v_name=matcher.group(1);
				}
				load_hero=true;
					
			}
			//∆•≈‰v_id
			if(load_hero && hero_string_list.get(i).contains("\"HeroID\"")){
				String mode = "\"HeroID\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.v_id=matcher.group(2);
					h.id=Integer.parseInt(h.v_id);
				}
			}
			//∆•≈‰role
			if(load_hero && hero_string_list.get(i).contains("\"Role\"")){
				String mode = "\"Role\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.role=matcher.group(2);
				}
			}
			//∆•≈‰role_lv
			if(load_hero && hero_string_list.get(i).contains("\"Rolelevels\"")){
				String mode = "\"Rolelevels\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.role_lv=matcher.group(2);
				}
			}
			//∆•≈‰team
			if(load_hero && hero_string_list.get(i).contains("\"Team\"")){
				String mode = "\"Team\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.team=matcher.group(2);
				}
			}
			//∆•≈‰ability_id
			if(load_hero && hero_string_list.get(i).contains("\"Ability")){
				String mode = "\"Ability([1-9]*)\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.ability_id=h.ability_id+matcher.group(3)+",";
				}
			}
			//∆•≈‰armor
			if(load_hero && hero_string_list.get(i).contains("\"ArmorPhysical\"")){
				String mode = "\"ArmorPhysical\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.armor=matcher.group(2);
				}
			}
			//∆•≈‰atk_cap
			if(load_hero && hero_string_list.get(i).contains("\"AttackCapabilities\"")){
				String mode = "\"AttackCapabilities\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.atk_cap=matcher.group(2);
				}
			}
			//∆•≈‰atk_min
			if(load_hero && hero_string_list.get(i).contains("\"AttackDamageMin\"")){
				String mode = "\"AttackDamageMin\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.atk_min=matcher.group(2);
				}
			}
			//∆•≈‰atk_max
			if(load_hero && hero_string_list.get(i).contains("\"AttackDamageMax\"")){
				String mode = "\"AttackDamageMax\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.atk_max=matcher.group(2);
				}
			}
			//∆•≈‰atk_rate
			if(load_hero && hero_string_list.get(i).contains("\"AttackRate\"")){
				String mode = "\"AttackRate\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.atk_rate=matcher.group(2);
				}
			}
			//∆•≈‰atk_range
			if(load_hero && hero_string_list.get(i).contains("\"AttackRange\"")){
				String mode = "\"AttackRange\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.atk_range=matcher.group(2);
				}
			}
			//∆•≈‰attr_primary
			if(load_hero && hero_string_list.get(i).contains("\"AttributePrimary\"")){
				String mode = "\"AttributePrimary\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.attr_primary=matcher.group(2);
				}
			}
			//∆•≈‰base_str
			if(load_hero && hero_string_list.get(i).contains("\"AttributeBaseStrength\"")){
				String mode = "\"AttributeBaseStrength\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.base_str=matcher.group(2);
				}
			}
			//∆•≈‰gain_str
			if(load_hero && hero_string_list.get(i).contains("\"AttributeStrengthGain\"")){
				String mode = "\"AttributeStrengthGain\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.gain_str=matcher.group(2);
				}
			}
			//∆•≈‰base_agi
			if(load_hero && hero_string_list.get(i).contains("\"AttributeBaseAgility\"")){
				String mode = "\"AttributeBaseAgility\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.base_agi=matcher.group(2);
				}
			}
			//∆•≈‰gain_agi
			if(load_hero && hero_string_list.get(i).contains("\"AttributeAgilityGain\"")){
				String mode = "\"AttributeAgilityGain\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.gain_agi=matcher.group(2);
				}
			}
			//∆•≈‰base_int
			if(load_hero && hero_string_list.get(i).contains("\"AttributeBaseIntelligence\"")){
				String mode = "\"AttributeBaseIntelligence\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.base_int=matcher.group(2);
				}
			}
			//∆•≈‰gain_int
			if(load_hero && hero_string_list.get(i).contains("\"AttributeIntelligenceGain\"")){
				String mode = "\"AttributeIntelligenceGain\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.gain_int=matcher.group(2);
				}
			}
			//∆•≈‰move_speed
			if(load_hero && hero_string_list.get(i).contains("\"MovementSpeed\"")){
				String mode = "\"MovementSpeed\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.move_speed=matcher.group(2);
				}
			}
			//∆•≈‰move_turnrate
			if(load_hero && hero_string_list.get(i).contains("\"MovementTurnRate\"")){
				String mode = "\"MovementTurnRate\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(hero_string_list.get(i));
				if (matcher.find()) {
					h.move_turnrate=matcher.group(2);
				}
			}
			//∆•≈‰name_sc
			if(load_hero && h.name_sc.equals("")){
				h.name_sc=findInSC("npc_dota_hero_"+h.v_name);
			}
			//∆•≈‰name_en
			if(load_hero && h.name_en.equals("")){
				h.name_en=findInSC("[english]npc_dota_hero_"+h.v_name);
			}
			//∆•≈‰bio_sc
			if(load_hero && h.bio_sc.equals("")){
				h.bio_sc=findInSC("npc_dota_hero_"+h.v_name+"_bio");
				h.bio_sc=h.bio_sc.replaceAll("##", "\n");
			}
			//∆•≈‰bio_en
			if(load_hero && h.bio_en.equals("")){
				h.bio_en=findInSC("[english]npc_dota_hero_"+h.v_name+"_bio");
				h.bio_en=h.bio_en.replaceAll("##", "\n").replaceAll("\\\\\"", "\"");
			}
			
			i++;
			continue;
		}
		WriteSQL.disconnect();
	}

	public void buildAbility(){
		int i=0;
		Ability a=new Ability();
		boolean load_ability=false;
		WriteSQL.connect();
		boolean special_ready=false;
		int special_quote=0;
		while(i+2<ability_string_list.size()){
			//∆•≈‰v_name
			if(i==ability_string_list.size()-4 || 
				(	ability_string_list.get(i).contains("//==") && 
					!ability_string_list.get(i+1).contains("//==") && ability_string_list.get(i+1).contains("// ") &&
					ability_string_list.get(i+2).contains("//=="))){
				
				if(load_ability){
					load_ability=false;
					System.out.println("∂¡»°ººƒ‹"+a.v_name+"ÕÍ±œ...");
					//±£¥Ê“—∂¡»°µΩµƒ…œ“ª∏ˆººƒ‹–≈œ¢
					WriteSQL.updateAbility(a);
					a=new Ability();
				}
				i=i+3;
				String mode = "\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.v_name=matcher.group(1);
				}
				if(checkValid(a.v_name)){
					load_ability=true;
				}	
			}
			//∆•≈‰v_id
			if(load_ability && ability_string_list.get(i).contains("\"ID\"")){
				String mode = "\"ID\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.v_id=matcher.group(2);
					a.id=Integer.parseInt(a.v_id);
				}
			}
			//∆•≈‰type
			if(load_ability && ability_string_list.get(i).contains("\"AbilityType\"")){
				String mode = "\"AbilityType\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.type=matcher.group(2);
				}
			}
			//∆•≈‰behavior
			if(load_ability && ability_string_list.get(i).contains("\"AbilityBehavior\"")){
				String mode = "\"AbilityBehavior\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.behavior=matcher.group(2);
				}
			}
			//∆•≈‰unit_target_team
			if(load_ability && ability_string_list.get(i).contains("\"AbilityUnitTargetTeam\"")){
				String mode = "\"AbilityUnitTargetTeam\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.unit_target_team=matcher.group(2);
				}
			}
			//∆•≈‰ability_unit_target_type
			if(load_ability && ability_string_list.get(i).contains("\"AbilityUnitTargetType\"")){
				String mode = "\"AbilityUnitTargetType\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.ability_unit_target_type=matcher.group(2);
				}
			}
			//∆•≈‰ability_unit_target_flags
			if(load_ability && ability_string_list.get(i).contains("\"AbilityUnitTargetFlags\"")){
				String mode = "\"AbilityUnitTargetFlags\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.ability_unit_target_flags=matcher.group(2);
				}
			}
			//∆•≈‰unit_damage_type
			if(load_ability && ability_string_list.get(i).contains("\"AbilityUnitDamageType\"")){
				String mode = "\"AbilityUnitDamageType\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.unit_damage_type=matcher.group(2);
				}
			}
			//∆•≈‰cast_range
			if(load_ability && ability_string_list.get(i).contains("\"AbilityCastRange\"")){
				String mode = "\"AbilityCastRange\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.cast_range=matcher.group(2);
				}
			}
			//∆•≈‰cast_range_buffer
			if(load_ability && ability_string_list.get(i).contains("\"AbilityCastRangeBuffer\"")){
				String mode = "\"AbilityCastRangeBuffer\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.cast_range_buffer=matcher.group(2);
				}
			}
			//∆•≈‰cast_point
			if(load_ability && ability_string_list.get(i).contains("\"AbilityCastPoint\"")){
				String mode = "\"AbilityCastPoint\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.cast_point=matcher.group(2);
				}
			}
			//∆•≈‰channel_time
			if(load_ability && ability_string_list.get(i).contains("\"AbilityChannelTime\"")){
				String mode = "\"AbilityChannelTime\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.channel_time=matcher.group(2);
				}
			}
			//∆•≈‰cool_down
			if(load_ability && ability_string_list.get(i).contains("\"AbilityCoolDown\"")){
				String mode = "\"AbilityCoolDown\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.cool_down=matcher.group(2);
				}
			}
			//∆•≈‰duration
			if(load_ability && ability_string_list.get(i).contains("\"AbilityDuration\"")){
				String mode = "\"AbilityDuration\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.duration=matcher.group(2);
				}
			}
			//∆•≈‰damage
			if(load_ability && ability_string_list.get(i).contains("\"AbilityDamege\"")){
				String mode = "\"AbilityDamege\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.damage=matcher.group(2);
				}
			}
			//∆•≈‰mana_cost
			if(load_ability && ability_string_list.get(i).contains("\"AbilityManaCost\"")){
				String mode = "\"AbilityManaCost\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.mana_cost=matcher.group(2);
				}
			}
			//∆•≈‰modifier_support_value
			if(load_ability && ability_string_list.get(i).contains("\"AbilityModifierSupportValue\"")){
				String mode = "\"AbilityModifierSupportValue\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.modifier_support_value=matcher.group(2);
				}
			}
			//∆•≈‰modifier_support_bonus
			if(load_ability && ability_string_list.get(i).contains("\"AbilityModifierSupportBonus\"")){
				String mode = "\"AbilityModifierSupportBonus\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(ability_string_list.get(i));
				if (matcher.find()) {
					a.modifier_support_bonus=matcher.group(2);
				}
			}
			
			
			//∆•≈‰name_sc
			if(load_ability && a.name_sc==null){
				a.name_sc=findInSC("DOTA_Tooltip_ability_"+a.v_name);
				if(a.name_sc.equals("")){
					a.name_sc=findInSC("DOTA_Tooltip_Ability_"+a.v_name);
				}
			}
			//∆•≈‰name_en
			if(load_ability && a.name_en==null){
				a.name_en=findInSC("[english]DOTA_Tooltip_ability_"+a.v_name);
				if(a.name_en.equals("")){
					a.name_en=findInSC("[english]DOTA_Tooltip_Ability_"+a.v_name);
				}
			}
			//∆•≈‰desc_sc
			if(load_ability && a.desc_sc==null){
				a.desc_sc=findInSC("DOTA_Tooltip_ability_"+a.v_name+"_Description");
				if(a.desc_sc.equals("")){
					a.desc_sc=findInSC("DOTA_Tooltip_ability_"+a.v_name+"_description");
				}
				if(a.desc_sc.equals("")){
					a.desc_sc=findInSC("DOTA_Tooltip_Ability_"+a.v_name+"_Description");
				}
				a.desc_sc=a.desc_sc.replaceAll("\\\\n", "\n").replaceAll("%%", "%");
				a.desc_sc=a.desc_sc.replaceAll("<font color='#7998b5'>", "").replaceAll("<font color='#d671a9'>", "");
				a.desc_sc=a.desc_sc.replaceAll("<font color='#cabe68'>", "").replaceAll("</font>", "");
			}
			//∆•≈‰desc_en
			if(load_ability && a.desc_en==null){
				a.desc_en=findInSC("[english]DOTA_Tooltip_ability_"+a.v_name+"_Description");
				if(a.desc_en.equals("")){
					a.desc_en=findInSC("[english]DOTA_Tooltip_ability_"+a.v_name+"_description");
				}
				if(a.desc_en.equals("")){
					a.desc_en=findInSC("[english]DOTA_Tooltip_Ability_"+a.v_name+"_Description");
				}
				a.desc_en=a.desc_en.replaceAll("\\\\n", "\n").replaceAll("%%", "%");
				a.desc_sc=a.desc_sc.replaceAll("<font color='#7998b5'>", "").replaceAll("<font color='#d671a9'>", "");
				a.desc_sc=a.desc_sc.replaceAll("<font color='#cabe68'>", "").replaceAll("</font>", "");
			}
			//∆•≈‰lore_sc
			if(load_ability && a.lore_sc==null){
				a.lore_sc=findInSC("DOTA_Tooltip_ability_"+a.v_name+"_Lore");
				if(a.lore_sc.length()<=1){
					a.lore_sc="";
				}
			}
			//∆•≈‰lore_en
			if(load_ability && a.lore_en==null){
				a.lore_en=findInSC("[english]DOTA_Tooltip_ability_"+a.v_name+"_Lore");
				if(a.lore_en.length()<=1){
					a.lore_en="";
				}
			}
			//∆•≈‰note_sc
			if(load_ability && a.note_sc==null){
				a.note_sc=findInSC("DOTA_Tooltip_ability_"+a.v_name+"_Note0");
				a.note_sc=a.note_sc+"\r\n"+findInSC("DOTA_Tooltip_ability_"+a.v_name+"_Note1");
				a.note_sc=a.note_sc+"\r\n"+findInSC("DOTA_Tooltip_ability_"+a.v_name+"_Note2");
				a.note_sc=a.note_sc+"\r\n"+findInSC("DOTA_Tooltip_ability_"+a.v_name+"_Note3");
				a.note_sc=a.note_sc+"\r\n"+findInSC("DOTA_Tooltip_ability_"+a.v_name+"_Note4");
			}
			//∆•≈‰note_en
			if(load_ability && a.note_en==null){
				a.note_en=findInSC("[english]DOTA_Tooltip_ability_"+a.v_name+"_Note0");
				a.note_en=a.note_en+"\r\n"+findInSC("[english]DOTA_Tooltip_ability_"+a.v_name+"_Note1");
				a.note_en=a.note_en+"\r\n"+findInSC("[english]DOTA_Tooltip_ability_"+a.v_name+"_Note2");
				a.note_en=a.note_en+"\r\n"+findInSC("[english]DOTA_Tooltip_ability_"+a.v_name+"_Note3");
				a.note_en=a.note_en+"\r\n"+findInSC("[english]DOTA_Tooltip_ability_"+a.v_name+"_Note4");
			}
			//∆•≈‰special_rawø™ º
			if(load_ability && ability_string_list.get(i).contains("\"AbilitySpecial\"")){
				special_ready=true;
			}
			if(load_ability && special_ready){
				if(ability_string_list.get(i).contains("{")){
					special_quote++;
				}
				if(ability_string_list.get(i).contains("}")){
					special_quote--;
					if(special_quote==0){
						if(a.special_raw.length()>1){
							
							String mode = "%([a-z_]*?)%";
							Pattern pattern = Pattern.compile(mode);
							Matcher matcher = pattern.matcher(a.desc_sc);
							while (matcher.find()) {
								String var=matcher.group(1);
								String t_mode = var+"=(.*?),";
								Pattern t_pattern = Pattern.compile(t_mode);
								Matcher t_matcher = t_pattern.matcher(a.special_raw);
								if (t_matcher.find()) {
									a.desc_sc=a.desc_sc.replaceAll("%"+var+"%", t_matcher.group(1));
									a.desc_en=a.desc_en.replaceAll("%"+var+"%", t_matcher.group(1));
								}
							}
							
							a.special_raw=a.special_raw.substring(0, a.special_raw.length()-1);
						}
						special_ready=false;

						
					}
				}
				if(ability_string_list.get(i).contains("\"var_type\"")){
					i++;
					
					String mode = "\"(.*?)\"([\t ]*)\"(.*?)\"";
					Pattern pattern = Pattern.compile(mode);
					Matcher matcher = pattern.matcher(ability_string_list.get(i));
					if (matcher.find()) {
						a.special_raw=a.special_raw+matcher.group(1)+"="+matcher.group(3)+",";
					}
				}
			}
			//∆•≈‰special_sc
			if(load_ability && !special_ready && a.special_raw!=null && a.special_raw.length()>0 && a.special_sc.equals("")){
				String[] sp_l=a.special_raw.split(",");
				for(int j=0;j<sp_l.length;j++){
					String[] t_l=sp_l[j].split("=");
					String ts=findInSC("DOTA_Tooltip_ability_"+a.v_name+"_"+t_l[0]);
					if(!ts.equals("")){
						if(ts.contains("%")){
							a.special_sc=a.special_sc+ts.replaceAll("%", "")+" "+t_l[1].replace(" ", "/")+"%\n";
						}else{
							a.special_sc=a.special_sc+ts+" "+t_l[1].replace(" ", "/")+"\n";
						}
					}
				}
			}
			//∆•≈‰special_en
			if(load_ability && !special_ready && a.special_raw!=null && a.special_raw.length()>0 && a.special_en.equals("")){
				String[] sp_l=a.special_raw.split(",");
				for(int j=0;j<sp_l.length;j++){
					String[] t_l=sp_l[j].split("=");
					String ts=findInSC("[english]DOTA_Tooltip_ability_"+a.v_name+"_"+t_l[0]);
					if(!ts.equals("")){
						if(ts.contains("%")){
							a.special_en=a.special_en+ts.replaceAll("%", "")+" "+t_l[1].replace(" ", "/")+"%\n";
						}else{
							a.special_en=a.special_en+ts+" "+t_l[1].replace(" ", "/")+"\n";
						}
					}
				}
			}
			
			i++;
			continue;
		}
		WriteSQL.disconnect();
	}
	
	public void buildItem(){
		int i=0;
		Item it=new Item();
		boolean load_item=false;
		WriteSQL.connect();
		boolean special_ready=false;
		boolean recipe_ready=false;
		int special_quote=0;
		int recipe_quote=0;
		while(i+2<item_string_list.size()){
			//∆•≈‰v_name
			if(i==item_string_list.size()-4 || 
				(	item_string_list.get(i).contains("//==") && 
					!item_string_list.get(i+1).contains("//==") && item_string_list.get(i+1).contains("// ") &&
					item_string_list.get(i+2).contains("//=="))		){
				
				if(load_item){
					load_item=false;
					System.out.println("∂¡»°ŒÔ∆∑"+it.v_name+"ÕÍ±œ...");
					//±£¥Ê“—∂¡»°µΩµƒ…œ“ª∏ˆŒÔ∆∑–≈œ¢
					WriteSQL.updateItem(it);
					it=new Item();
				}
				i=i+3;
				String mode = "\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(item_string_list.get(i));
				if (matcher.find()) {
					it.v_name=matcher.group(1);
				}
				if(checkValid(it.v_name)){
					load_item=true;
				}	
			}
			//∆•≈‰v_id
			if(load_item && item_string_list.get(i).contains("\"ID\"")){
				String mode = "\"ID\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(item_string_list.get(i));
				if (matcher.find()) {
					it.v_id=matcher.group(2);
					it.id=Integer.parseInt(it.v_id);
				}
			}
			//∆•≈‰behavior
			if(load_item && item_string_list.get(i).contains("\"AbilityBehavior\"")){
				String mode = "\"AbilityBehavior\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(item_string_list.get(i));
				if (matcher.find()) {
					it.behavior=matcher.group(2);
				}
			}
			//∆•≈‰cost
			if(load_item && item_string_list.get(i).contains("\"ItemCost\"")){
				String mode = "\"ItemCost\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(item_string_list.get(i));
				if (matcher.find()) {
					it.cost=matcher.group(2);
				}
			}
			//∆•≈‰shop_tag
			if(load_item && item_string_list.get(i).contains("\"ItemShopTags\"")){
				String mode = "\"ItemShopTags\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(item_string_list.get(i));
				if (matcher.find()) {
					it.shop_tag=matcher.group(2);
				}
			}
			//∆•≈‰quality
			if(load_item && item_string_list.get(i).contains("\"ItemQuality\"")){
				String mode = "\"ItemQuality\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(item_string_list.get(i));
				if (matcher.find()) {
					it.quality=matcher.group(2);
				}
			}
			//∆•≈‰declaration
			if(load_item && item_string_list.get(i).contains("\"ItemDeclarations\"")){
				String mode = "\"ItemDeclarations\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(item_string_list.get(i));
				if (matcher.find()) {
					it.declaration=matcher.group(2);
				}
			}
			//∆•≈‰side_shop
			if(load_item && item_string_list.get(i).contains("\"SideShop\"")){
				String mode = "\"SideShop\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(item_string_list.get(i));
				if (matcher.find()) {
					it.side_shop=matcher.group(2);
				}
			}
			//∆•≈‰secret_shop
			if(load_item && item_string_list.get(i).contains("\"SecretShop\"")){
				String mode = "\"SecretShop\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(item_string_list.get(i));
				if (matcher.find()) {
					it.secret_shop=matcher.group(2);
				}
			}
			//∆•≈‰cast_range
			if(load_item && item_string_list.get(i).contains("\"AbilityCastRange\"")){
				String mode = "\"AbilityCastRange\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(item_string_list.get(i));
				if (matcher.find()) {
					it.cast_range=matcher.group(2);
				}
			}
			//∆•≈‰cast_point
			if(load_item && item_string_list.get(i).contains("\"AbilityCastPoint\"")){
				String mode = "\"AbilityCastPoint\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(item_string_list.get(i));
				if (matcher.find()) {
					it.cast_point=matcher.group(2);
				}
			}
			//∆•≈‰cool_down
			if(load_item && item_string_list.get(i).contains("\"AbilityCooldown\"")){
				String mode = "\"AbilityCooldown\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(item_string_list.get(i));
				if (matcher.find()) {
					it.cool_down=matcher.group(2);
				}
			}
			//∆•≈‰mana_cost
			if(load_item && item_string_list.get(i).contains("\"AbilityManaCost\"")){
				String mode = "\"AbilityManaCost\"([\t ]*)\"(.*?)\"";
				Pattern pattern = Pattern.compile(mode);
				Matcher matcher = pattern.matcher(item_string_list.get(i));
				if (matcher.find()) {
					it.mana_cost=matcher.group(2);
				}
			}
			
			//∆•≈‰name_sc
			if(load_item && it.name_sc==null){
				it.name_sc=findInSC("DOTA_Tooltip_ability_"+it.v_name);
				if(it.name_sc.equals("")){
					it.name_sc=findInSC("DOTA_Tooltip_Ability_"+it.v_name);
				}
			}
			//∆•≈‰name_en
			if(load_item && it.name_en==null){
				it.name_en=findInSC("[english]DOTA_Tooltip_ability_"+it.v_name);
				if(it.name_en.equals("")){
					it.name_en=findInSC("[english]DOTA_Tooltip_Ability_"+it.v_name);
				}
			}
			//∆•≈‰desc_sc
			if(load_item && it.desc_sc==null){
				it.desc_sc=findInSC("DOTA_Tooltip_ability_"+it.v_name+"_Description");
				if(it.desc_sc.equals("")){
					it.desc_sc=findInSC("DOTA_Tooltip_ability_"+it.v_name+"_description");
				}
				if(it.desc_sc.equals("")){
					it.desc_sc=findInSC("DOTA_Tooltip_Ability_"+it.v_name+"_Description");
				}
				it.desc_sc=it.desc_sc.replaceAll("\\\\n", "\n").replaceAll("%%", "%");
				if(it.desc_sc.length()<=1){
					it.desc_sc="";
				}
			}
			//∆•≈‰desc_en
			if(load_item && it.desc_en==null){
				it.desc_en=findInSC("[english]DOTA_Tooltip_ability_"+it.v_name+"_Description");
				if(it.desc_en.equals("")){
					it.desc_en=findInSC("[english]DOTA_Tooltip_ability_"+it.v_name+"_description");
				}
				if(it.desc_en.equals("")){
					it.desc_en=findInSC("[english]DOTA_Tooltip_Ability_"+it.v_name+"_Description");
				}
				it.desc_en=it.desc_en.replaceAll("\\\\n", "\n").replaceAll("%%", "%");
				if(it.desc_en.length()<=1){
					it.desc_en="";
				}
			}
			//∆•≈‰lore_sc
			if(load_item && it.lore_sc==null){
				it.lore_sc=findInSC("DOTA_Tooltip_ability_"+it.v_name+"_Lore");
				if(it.lore_sc.length()<=1){
					it.lore_sc="";
				}
			}
			//∆•≈‰lore_en
			if(load_item && it.lore_en==null){
				it.lore_en=findInSC("[english]DOTA_Tooltip_ability_"+it.v_name+"_Lore");
				if(it.lore_en.length()<=1){
					it.lore_en="";
				}
			}
			//∆•≈‰note_sc
			if(load_item && it.note_sc==null){
				it.note_sc=findInSC("DOTA_Tooltip_ability_"+it.v_name+"_Note0");
				it.note_sc=it.note_sc+"\r\n"+findInSC("DOTA_Tooltip_ability_"+it.v_name+"_Note1");
				it.note_sc=it.note_sc+"\r\n"+findInSC("DOTA_Tooltip_ability_"+it.v_name+"_Note2");
				it.note_sc=it.note_sc+"\r\n"+findInSC("DOTA_Tooltip_ability_"+it.v_name+"_Note3");
				it.note_sc=it.note_sc+"\r\n"+findInSC("DOTA_Tooltip_ability_"+it.v_name+"_Note4");
			}
			//∆•≈‰note_en
			if(load_item && it.note_en==null){
				it.note_en=findInSC("[english]DOTA_Tooltip_ability_"+it.v_name+"_Note0");
				it.note_en=it.note_en+"\r\n"+findInSC("[english]DOTA_Tooltip_ability_"+it.v_name+"_Note1");
				it.note_en=it.note_en+"\r\n"+findInSC("[english]DOTA_Tooltip_ability_"+it.v_name+"_Note2");
				it.note_en=it.note_en+"\r\n"+findInSC("[english]DOTA_Tooltip_ability_"+it.v_name+"_Note3");
				it.note_en=it.note_en+"\r\n"+findInSC("[english]DOTA_Tooltip_ability_"+it.v_name+"_Note4");
			}
			//∆•≈‰special_rawø™ º
			if(load_item && item_string_list.get(i).contains("\"AbilitySpecial\"")){
				special_ready=true;
			}
			if(load_item && special_ready){
				if(item_string_list.get(i).contains("{")){
					special_quote++;
				}
				if(item_string_list.get(i).contains("}")){
					special_quote--;
					if(special_quote==0){
						if(it.special_raw.length()>1){
							
							String mode = "%([a-z_]*?)%";
							Pattern pattern = Pattern.compile(mode);
							Matcher matcher = pattern.matcher(it.desc_sc);
							while (matcher.find()) {
								String var=matcher.group(1);
								String t_mode = var+"=(.*?),";
								Pattern t_pattern = Pattern.compile(t_mode);
								Matcher t_matcher = t_pattern.matcher(it.special_raw);
								if (t_matcher.find()) {
									it.desc_sc=it.desc_sc.replaceAll("%"+var+"%", t_matcher.group(1).replaceAll(" ", "/"));
									it.desc_en=it.desc_en.replaceAll("%"+var+"%", t_matcher.group(1).replaceAll(" ", "/"));
								}
							}
							
							it.special_raw=it.special_raw.substring(0, it.special_raw.length()-1);
						}
						special_ready=false;
					}
				}
				if(item_string_list.get(i).contains("\"var_type\"")){
					i++;
					
					String mode = "\"(.*?)\"([\t ]*)\"(.*?)\"";
					Pattern pattern = Pattern.compile(mode);
					Matcher matcher = pattern.matcher(item_string_list.get(i));
					if (matcher.find()) {
						it.special_raw=it.special_raw+matcher.group(1)+"="+matcher.group(3)+",";
					}
				}
			}
			//∆•≈‰special_sc
			if(load_item && !special_ready && it.special_raw!=null && it.special_raw.length()>0 && it.special_sc.equals("")){
				String[] sp_l=it.special_raw.split(",");
				for(int j=0;j<sp_l.length;j++){
					String[] t_l=sp_l[j].split("=");
					String ts=findInSC("DOTA_Tooltip_ability_"+it.v_name+"_"+t_l[0]);
					if(!ts.equals("")){
						if(ts.contains("$")){
							String var =ts.substring(ts.indexOf("$")+1,ts.length());
							ts=findInSC("dota_ability_variable_"+var);
							it.special_sc=it.special_sc+"+"+t_l[1].replace(" ", "/")+" "+ts+"\n";
						}else if(ts.contains("%")){
							it.special_sc=it.special_sc+ts.replaceAll("%", "")+" "+t_l[1].replace(" ", "/")+"%\n";
						}else{
							it.special_sc=it.special_sc+ts+" "+t_l[1].replace(" ", "/")+"\n";
						}
					}
				}
				
			}
			//∆•≈‰special_en
			if(load_item && !special_ready && it.special_raw!=null && it.special_raw.length()>0 && it.special_en.equals("")){
				String[] sp_l=it.special_raw.split(",");
				for(int j=0;j<sp_l.length;j++){
					String[] t_l=sp_l[j].split("=");
					String ts=findInSC("[english]DOTA_Tooltip_ability_"+it.v_name+"_"+t_l[0]);
					if(!ts.equals("")){
						if(ts.contains("$")){
							String var =ts.substring(ts.indexOf("$")+1,ts.length());
							ts=findInSC("[english]dota_ability_variable_"+var);
							it.special_en=it.special_en+"+"+t_l[1].replace(" ", "/")+" "+ts+"\n";
						}else if(ts.contains("%")){
							it.special_en=it.special_en+ts.replaceAll("%", "")+" "+t_l[1].replace(" ", "/")+"%\n";
						}else{
							it.special_en=it.special_en+ts+" "+t_l[1].replace(" ", "/")+"\n";
						}
					}
				}
			}
			//∆•≈‰recipe_rawø™ º
			if(load_item && item_string_list.get(i).contains("\"ItemRecipe\"") && it.recipe_raw.equals("")){
				recipe_ready=true;
			}
			if(load_item && recipe_ready){
				if(item_string_list.get(i).contains("{")){
					recipe_quote++;
				}
				if(item_string_list.get(i).contains("}")){
					recipe_quote--;
					if(recipe_quote==0){
						if(it.recipe_raw.length()>1){
							it.recipe_raw=it.recipe_raw.substring(0, it.recipe_raw.length()-1);
						}
						recipe_ready=false;
					}
				}
				if(item_string_list.get(i).contains("01") || item_string_list.get(i).contains("02") || item_string_list.get(i).contains("03")){
					
					String mode = "\"([0-9]*?)\"([\t ]*)\"(.*?)\"";
					Pattern pattern = Pattern.compile(mode);
					Matcher matcher = pattern.matcher(item_string_list.get(i));
					if (matcher.find()) {
						it.recipe_raw=it.recipe_raw+matcher.group(1)+"="+matcher.group(3)+",";
					}
				}
			}
			
			i++;
			continue;
		}
		WriteSQL.disconnect();
	}
	
	String findInSC(String keyword){
		String keyword_sc="";
		int i=0;
		while(i<sc_string_list.size()){
			if(sc_string_list.get(i).contains("\""+keyword+"\"")){
				String mode = "\"(\t*)\"(.*)\"";
				Pattern pattern = Pattern.compile(mode,Pattern.CASE_INSENSITIVE);
				
				String tts=sc_string_list.get(i);
				while(tts.lastIndexOf("\"")!=tts.length()-1 || (tts.lastIndexOf("\"")==tts.length()-1) && (tts.lastIndexOf("\\")==tts.length()-2)){
					i++;
					tts+="##"+sc_string_list.get(i);
				}
				Matcher matcher = pattern.matcher(tts);
				if (matcher.find()) {
					keyword_sc=matcher.group(2);
				}
				break;
			}else{
				i++;
			}
		}
		return keyword_sc;
	}
	
	boolean checkValid(String v_name){
		if(v_name!=null){
			if(v_name.contains("rubick_empty") || v_name.contains("rubick_hidden") || v_name.contains("attribute_bonus")
					|| v_name.contains("default_attack") || v_name.contains("roshan_") 
					|| v_name.contains("greevil_") || v_name.contains("throw_")
					|| v_name.contains("_empty1") || v_name.contains("_empty2")
					|| v_name.contains("backdoor_") || v_name.contains("courier_")){
				return false;
			}
		}else{
			return false;
		}
		return true;
	}
}
