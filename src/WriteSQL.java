import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;


public class WriteSQL {

	static Connection conn;
	static PreparedStatement pstmt=null;
	static ResultSet rs=null;
	
	public static void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydota2?useUnicode=true&characterEncoding=gbk","root","123456"); 
			buildHeroTable();
			buildAbilityTable();
			buildItemTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void disconnect(){
		try {
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void buildHeroTable(){
		try {
			pstmt = (PreparedStatement) conn.prepareStatement("CREATE TABLE IF NOT EXISTS heroes ("+
					 " `id` int(5) NOT NULL AUTO_INCREMENT, " +
					 " `v_name` varchar(20) DEFAULT NULL, " +
					 " `v_id` varchar(20) DEFAULT NULL, " +
					 " `role` varchar(50) DEFAULT NULL, " +
					 " `role_lv` varchar(20) DEFAULT NULL, " +
					 " `team` varchar(5) DEFAULT NULL, " +
					 " `ability_id` varchar(500) DEFAULT NULL, " +
					 " `armor` varchar(20) DEFAULT NULL, " +
					 " `atk_cap` varchar(50) DEFAULT NULL, " +
					 " `atk_min` varchar(50) DEFAULT NULL, " +
					 " `atk_max` varchar(50) DEFAULT NULL, " +
					 " `atk_rate` varchar(20) DEFAULT NULL, " +
					 " `atk_range` varchar(20) DEFAULT NULL, " +
					 " `attr_primary` varchar(50) DEFAULT NULL, " +
					 " `base_str` varchar(20) DEFAULT NULL, " +
					 " `gain_str` varchar(20) DEFAULT NULL, " +
					 " `base_agi` varchar(20) DEFAULT NULL, " +
					 " `gain_agi` varchar(20) DEFAULT NULL, " +
					 " `base_int` varchar(20) DEFAULT NULL, " +
					 " `gain_int` varchar(20) DEFAULT NULL, " +
					 " `move_speed` varchar(20) DEFAULT NULL, " +
					 " `move_turnrate` varchar(20) DEFAULT NULL, " +
					 " `name_sc` varchar(50) DEFAULT NULL, " +
					 " `name_en` varchar(50) DEFAULT NULL, " +
					 " `bio_sc` varchar(800) DEFAULT NULL, " +
					 " `bio_en` varchar(3000) DEFAULT NULL, " +
					 " PRIMARY KEY (`id`)" +
					 ") ENGINE=InnoDB DEFAULT CHARSET=gbk;");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void buildAbilityTable(){
		try {
			pstmt = (PreparedStatement) conn.prepareStatement("CREATE TABLE IF NOT EXISTS abilities ("+
					 " `id` int(5) NOT NULL AUTO_INCREMENT, " +
					 " `v_name` varchar(50) DEFAULT NULL, " +
					 " `v_id` varchar(20) DEFAULT NULL, " +
					 " `type` varchar(50) DEFAULT NULL, " +
					 " `behavior` varchar(400) DEFAULT NULL, " +
					 " `unit_target_team` varchar(300) DEFAULT NULL, " +
					 " `ability_unit_target_type` varchar(200) DEFAULT NULL, " +
					 " `ability_unit_target_flags` varchar(200) DEFAULT NULL, " +
					 " `unit_damage_type` varchar(50) DEFAULT NULL, " +
					 " `cast_range` varchar(20) DEFAULT NULL, " +
					 " `cast_range_buffer` varchar(50) DEFAULT NULL, " +
					 " `cast_point` varchar(50) DEFAULT NULL, " +
					 " `channel_time` varchar(20) DEFAULT NULL, " +
					 " `cool_down` varchar(20) DEFAULT NULL, " +
					 " `duration` varchar(50) DEFAULT NULL, " +
					 " `shared_cool_down` varchar(20) DEFAULT NULL, " +
					 " `damage` varchar(20) DEFAULT NULL, " +
					 " `mana_cost` varchar(20) DEFAULT NULL, " +
					 " `modifier_support_value` varchar(20) DEFAULT NULL, " +
					 " `modifier_support_bonus` varchar(20) DEFAULT NULL, " +
					 " `name_sc` varchar(50) DEFAULT NULL, " +
					 " `name_en` varchar(50) DEFAULT NULL, " +
					 " `desc_sc` varchar(500) DEFAULT NULL, " +
					 " `desc_en` varchar(1000) DEFAULT NULL, " +
					 " `lore_sc` varchar(100) DEFAULT NULL, " +
					 " `lore_en` varchar(200) DEFAULT NULL, " +
					 " `note_sc` varchar(300) DEFAULT NULL, " +
					 " `note_en` varchar(600) DEFAULT NULL, " +
					 " `special_raw` varchar(1000) DEFAULT NULL, " +
					 " `special_sc` varchar(1000) DEFAULT NULL, " +
					 " `special_en` varchar(2000) DEFAULT NULL, " +
					 " PRIMARY KEY (`id`)" +
					 ") ENGINE=InnoDB DEFAULT CHARSET=gbk;");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void buildItemTable(){
		try {
			pstmt = (PreparedStatement) conn.prepareStatement("CREATE TABLE IF NOT EXISTS items ("+
					 " `id` int(5) NOT NULL AUTO_INCREMENT, " +
					 " `v_name` varchar(50) DEFAULT NULL, " +
					 " `v_id` varchar(20) DEFAULT NULL, " +
					 " `behavior` varchar(400) DEFAULT NULL, " +
					 " `cost` varchar(50) DEFAULT NULL, " +
					 " `shop_tag` varchar(100) DEFAULT NULL, " +
					 " `quality` varchar(30) DEFAULT NULL, " +
					 " `declaration` varchar(200) DEFAULT NULL, " +
					 " `side_shop` varchar(20) DEFAULT NULL, " +
					 " `secret_shop` varchar(20) DEFAULT NULL, " +
					 " `cast_range` varchar(20) DEFAULT NULL, " +
					 " `cast_point` varchar(50) DEFAULT NULL, " +
					 " `cool_down` varchar(50) DEFAULT NULL, " +
					 " `mana_cost` varchar(20) DEFAULT NULL, " +
					 " `name_sc` varchar(50) DEFAULT NULL, " +
					 " `name_en` varchar(50) DEFAULT NULL, " +
					 " `desc_sc` varchar(500) DEFAULT NULL, " +
					 " `desc_en` varchar(1000) DEFAULT NULL, " +
					 " `lore_sc` varchar(100) DEFAULT NULL, " +
					 " `lore_en` varchar(200) DEFAULT NULL, " +
					 " `note_sc` varchar(300) DEFAULT NULL, " +
					 " `note_en` varchar(600) DEFAULT NULL, " +
					 " `special_raw` varchar(1000) DEFAULT NULL, " +
					 " `special_sc` varchar(1000) DEFAULT NULL, " +
					 " `special_en` varchar(2000) DEFAULT NULL, " +
					 " `recipe_raw` varchar(1000) DEFAULT NULL, " +
					 " `recipe` varchar(500) DEFAULT NULL, " +
					 " PRIMARY KEY (`id`)" +
					 ") ENGINE=InnoDB DEFAULT CHARSET=gbk;");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateHero(Hero h){
		try {
			pstmt = (PreparedStatement) conn.prepareStatement("SELECT * from heroes WHERE id=?");
			pstmt.setInt(1,h.id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				pstmt = (PreparedStatement) conn.prepareStatement("UPDATE heroes SET v_name=?, v_id=?,"
						+ "role=?, role_lv=?, team=?, ability_id=?, armor=?, atk_cap=?, atk_min=?, atk_max=?,"
						+ "atk_rate=?, atk_range=?, attr_primary=?, base_str=?, gain_str=?, base_agi=?, gain_agi=?, base_int=?, gain_int=?,"
						+ "move_speed=?, move_turnrate=?, name_sc=?, name_en=?, bio_sc=?, bio_en=? WHERE id=?");
				pstmt.setString(1,h.v_name);
				pstmt.setString(2,h.v_id);
				pstmt.setString(3,h.role);
				pstmt.setString(4,h.role_lv);
				pstmt.setString(5,h.team);
				pstmt.setString(6,h.ability_id);
				pstmt.setString(7,h.armor);
				pstmt.setString(8,h.atk_cap);
				pstmt.setString(9,h.atk_min);
				pstmt.setString(10,h.atk_max);
				pstmt.setString(11,h.atk_rate);
				pstmt.setString(12,h.atk_range);
				pstmt.setString(13,h.attr_primary);
				pstmt.setString(14,h.base_str);
				pstmt.setString(15,h.gain_str);
				pstmt.setString(16,h.base_agi);
				pstmt.setString(17,h.gain_agi);
				pstmt.setString(18,h.base_int);
				pstmt.setString(19,h.gain_int);
				pstmt.setString(20,h.move_speed);
				pstmt.setString(21,h.move_turnrate);
				pstmt.setString(22, h.name_sc);
				pstmt.setString(23, h.name_en);
				pstmt.setString(24, h.bio_sc);
				pstmt.setString(25, h.bio_en);
				pstmt.setInt(26,h.id);
				pstmt.execute();
			}else{
				pstmt=(PreparedStatement) conn.prepareStatement("INSERT into heroes (id,v_name,v_id,"
					+ "role,role_lv,team,ability_id,armor,atk_cap,atk_min,atk_max," +
					"atk_rate,atk_range,attr_primary,base_str,gain_str,base_agi,gain_agi,base_int,gain_int," +
					"move_speed,move_turnrate,name_sc,name_en,bio_sc,bio_en) " +	
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
				pstmt.setInt(1,h.id);
				pstmt.setString(2, h.v_name);
				pstmt.setString(3, h.v_id);
				pstmt.setString(4, h.role);
				pstmt.setString(5, h.role_lv);
				pstmt.setString(6, h.team);
				pstmt.setString(7, h.ability_id);
				pstmt.setString(8, h.armor);
				pstmt.setString(9, h.atk_cap);
				pstmt.setString(10, h.atk_min);
				pstmt.setString(11, h.atk_max);
				pstmt.setString(12, h.atk_rate);
				pstmt.setString(13, h.atk_range);
				pstmt.setString(14, h.attr_primary);
				pstmt.setString(15, h.base_str);
				pstmt.setString(16, h.gain_str);
				pstmt.setString(17, h.base_agi);
				pstmt.setString(18, h.gain_agi);
				pstmt.setString(19, h.base_int);
				pstmt.setString(20, h.gain_int);
				pstmt.setString(21, h.move_speed);
				pstmt.setString(22, h.move_turnrate);
				pstmt.setString(23, h.name_sc);
				pstmt.setString(24, h.name_en);
				pstmt.setString(25, h.bio_sc);
				pstmt.setString(26, h.bio_en);
				pstmt.execute();
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateAbility(Ability a){
		try {
			pstmt = (PreparedStatement) conn.prepareStatement("SELECT * from abilities WHERE id=?");
			pstmt.setInt(1,a.id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				pstmt = (PreparedStatement) conn.prepareStatement("UPDATE abilities SET v_name=?, v_id=?,"
						+ "type=?, behavior=?, unit_target_team=?, ability_unit_target_type=?, ability_unit_target_flags=?, "
						+ "unit_damage_type=?, cast_range=?, cast_range_buffer=?,"
						+ "cast_point=?, channel_time=?, cool_down=?, duration=?, shared_cool_down=?, damage=?, "
						+ "mana_cost=?, modifier_support_value=?, modifier_support_bonus=?,"
						+ "name_sc=?, name_en=?, desc_sc=?, desc_en=?, lore_sc=?, lore_en=?, note_sc=?, note_en=? , "
						+ "special_raw=?, special_sc=?, special_en=? WHERE id=?");
				pstmt.setString(1,a.v_name);
				pstmt.setString(2,a.v_id);
				pstmt.setString(3,a.type);
				pstmt.setString(4,a.behavior);
				pstmt.setString(5,a.unit_target_team);
				pstmt.setString(6,a.ability_unit_target_type);
				pstmt.setString(7,a.ability_unit_target_flags);
				pstmt.setString(8,a.unit_damage_type);
				pstmt.setString(9,a.cast_range);
				pstmt.setString(10,a.cast_range_buffer);
				pstmt.setString(11,a.cast_point);
				pstmt.setString(12,a.channel_time);
				pstmt.setString(13,a.cool_down);
				pstmt.setString(14,a.duration);
				pstmt.setString(15,a.shared_cool_down);
				pstmt.setString(16,a.damage);
				pstmt.setString(17,a.mana_cost);
				pstmt.setString(18,a.modifier_support_value);
				pstmt.setString(19,a.modifier_support_bonus);
				pstmt.setString(20,a.name_sc);
				pstmt.setString(21,a.name_en);
				pstmt.setString(22, a.desc_sc);
				pstmt.setString(23, a.desc_en);
				pstmt.setString(24, a.lore_sc);
				pstmt.setString(25, a.lore_en);
				pstmt.setString(26, a.note_sc);
				pstmt.setString(27, a.note_en);
				pstmt.setString(28, a.special_raw);
				pstmt.setString(29, a.special_sc);
				pstmt.setString(30, a.special_en);
				pstmt.setInt(31,a.id);
				pstmt.execute();
			}else{
				pstmt=(PreparedStatement) conn.prepareStatement("INSERT into abilities (id,v_name,v_id,"
					+ "type,behavior,unit_target_team,ability_unit_target_type,ability_unit_target_flags," 
					+ "unit_damage_type,cast_range,cast_range_buffer,"
					+ "cast_point,channel_time,cool_down,duration,shared_cool_down,damage,"
					+ "mana_cost,modifier_support_value,modifier_support_bonus,"
					+ "name_sc,name_en,desc_sc,desc_en,lore_sc,lore_en,note_sc,note_en,"
					+ "special_raw,special_sc,special_en) " 
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
				pstmt.setInt(1,a.id);
				pstmt.setString(2,a.v_name);
				pstmt.setString(3,a.v_id);
				pstmt.setString(4,a.type);
				pstmt.setString(5,a.behavior);
				pstmt.setString(6,a.unit_target_team);
				pstmt.setString(7,a.ability_unit_target_type);
				pstmt.setString(8,a.ability_unit_target_flags);
				pstmt.setString(9,a.unit_damage_type);
				pstmt.setString(10,a.cast_range);
				pstmt.setString(11,a.cast_range_buffer);
				pstmt.setString(12,a.cast_point);
				pstmt.setString(13,a.channel_time);
				pstmt.setString(14,a.cool_down);
				pstmt.setString(15,a.duration);
				pstmt.setString(16,a.shared_cool_down);
				pstmt.setString(17,a.damage);
				pstmt.setString(18,a.mana_cost);
				pstmt.setString(19,a.modifier_support_value);
				pstmt.setString(20,a.modifier_support_bonus);
				pstmt.setString(21,a.name_sc);
				pstmt.setString(22,a.name_en);
				pstmt.setString(23, a.desc_sc);
				pstmt.setString(24, a.desc_en);
				pstmt.setString(25, a.lore_sc);
				pstmt.setString(26, a.lore_en);
				pstmt.setString(27, a.note_sc);
				pstmt.setString(28, a.note_en);
				pstmt.setString(29, a.special_raw);
				pstmt.setString(30, a.special_sc);
				pstmt.setString(31, a.special_en);
				pstmt.execute();
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateItem(Item it){
		try {
			pstmt = (PreparedStatement) conn.prepareStatement("SELECT * from items WHERE id=?");
			pstmt.setInt(1,it.id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				pstmt = (PreparedStatement) conn.prepareStatement("UPDATE items SET v_name=?, v_id=?,"
						+ "behavior=?, cost=?, shop_tag=?, quality=?, declaration=?, "
						+ "side_shop=?, secret_shop=?, cast_range=?, cast_point=?, cool_down=?, mana_cost=?, "
						+ "name_sc=?, name_en=?, desc_sc=?, desc_en=?, lore_sc=?, lore_en=?, note_sc=?, note_en=?, "
						+ "special_raw=?, special_sc=?, special_en=?, recipe_raw=?, recipe=?  WHERE id=?");
				pstmt.setString(1,it.v_name);
				pstmt.setString(2,it.v_id);
				pstmt.setString(3,it.behavior);
				pstmt.setString(4,it.cost);
				pstmt.setString(5,it.shop_tag);
				pstmt.setString(6,it.quality);
				pstmt.setString(7,it.declaration);
				pstmt.setString(8,it.side_shop);
				pstmt.setString(9,it.secret_shop);
				pstmt.setString(10,it.cast_range);
				pstmt.setString(11,it.cast_point);
				pstmt.setString(12,it.cool_down);
				pstmt.setString(13,it.mana_cost);
				pstmt.setString(14,it.name_sc);
				pstmt.setString(15,it.name_en);
				pstmt.setString(16,it.desc_sc);
				pstmt.setString(17,it.desc_en);
				pstmt.setString(18,it.lore_sc);
				pstmt.setString(19,it.lore_en);
				pstmt.setString(20,it.note_sc);
				pstmt.setString(21,it.note_en);
				pstmt.setString(22,it.special_raw);
				pstmt.setString(23,it.special_sc);
				pstmt.setString(24, it.special_en);
				pstmt.setString(25, it.recipe_raw);
				pstmt.setString(26, it.recipe);
				pstmt.setInt(27,it.id);
				pstmt.execute();
			}else{
				pstmt=(PreparedStatement) conn.prepareStatement("INSERT into items (id,v_name,v_id,"
					+ "behavior,cost,shop_tag,quality,declaration,side_shop,secret_shop,"
					+ "cast_range,cast_point,cool_down,mana_cost,name_sc,name_en,desc_sc,desc_en,lore_sc,lore_en,note_sc,note_en,"
					+ "special_raw,special_sc,special_en,recipe_raw,recipe) " 
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
				pstmt.setInt(1,it.id);
				pstmt.setString(2,it.v_name);
				pstmt.setString(3,it.v_id);
				pstmt.setString(4,it.behavior);
				pstmt.setString(5,it.cost);
				pstmt.setString(6,it.shop_tag);
				pstmt.setString(7,it.quality);
				pstmt.setString(8,it.declaration);
				pstmt.setString(9,it.side_shop);
				pstmt.setString(10,it.secret_shop);
				pstmt.setString(11,it.cast_range);
				pstmt.setString(12,it.cast_point);
				pstmt.setString(13,it.cool_down);
				pstmt.setString(14,it.mana_cost);
				pstmt.setString(15,it.name_sc);
				pstmt.setString(16,it.name_en);
				pstmt.setString(17,it.desc_sc);
				pstmt.setString(18,it.desc_en);
				pstmt.setString(19,it.lore_sc);
				pstmt.setString(20,it.lore_en);
				pstmt.setString(21,it.note_sc);
				pstmt.setString(22,it.note_en);
				pstmt.setString(23,it.special_raw);
				pstmt.setString(24,it.special_sc);
				pstmt.setString(25, it.special_en);
				pstmt.setString(26, it.recipe_raw);
				pstmt.setString(27, it.recipe);
				pstmt.execute();
			}
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
