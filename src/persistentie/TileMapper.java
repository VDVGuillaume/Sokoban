package persistentie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domein.GameBoard;
import domein.Tile;
import domein.TileTypes;

public class TileMapper extends BaseMapper {
	public Tile[][] getTiles(int gameBoardId) {
		Tile[][] tiles = new Tile[10][10];

		PreparedStatement stmt = null;
		Connection conn = null;
		final String sql = "select row_index, column_index, type, contains_player from TILE where gameboard_id = ? order by row_index, column_index";

		try {
			conn = createConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, gameBoardId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				var rowIndex = rs.getInt(1);
				var columnIndex = rs.getInt(2);
				TileTypes type = TileTypes.valueOf(rs.getString(3));
				var containsPlayer = rs.getBoolean(4);

				tiles[rowIndex][columnIndex] = new Tile(type, containsPlayer);
			}
		} catch (SQLException e) {
			// java...
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				// java...
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// java...
				e.printStackTrace();
			}
		}

		return tiles;
	}

	// TODO change GameBoard to Tiles[][]
	public void insertTiles(GameBoard gameboard, int gameboardID) {

		Connection conn = null;

		try {
			conn = createConnection();
			Statement stmt = conn.createStatement();
			String[][] tiles = new String[10][10];
			// TODO why are you using getCurrentState?
			tiles = gameboard.getCurrentState();
			int x = 0;
			int y = 0;
			String sql = new String();
			for (String[] tileRow : tiles) {
				for (String tile : tileRow) {
					// TODO code in if-statement is broken
					if (tiles.equals("Pawn")) {
						sql = String.format(
								"INSERT INTO TILE(gameboard_id, row_index, column_index, type, contains_player) VALUES (%d, %d, %d, '%s', %d)",
								gameboardID, x, y, "None", 1);

					} else {

						sql = String.format(
								"INSERT INTO TILE(gameboard_id, row_index, column_index, type, contains_player) VALUES (%d, %d, %d, '%s', %d)",
								gameboardID, x, y, tile, 0);
					}
					stmt.addBatch(sql);
					if (x == 9) {
						x = 0;
					} else {
						x++;
					}
				}
				y++;
			}
			stmt.executeBatch();

		} catch (SQLException e) {
			// java...
			e.printStackTrace();
		}
	}

	public void updateTiles(int gameBoardId, Tile[][] tiles) {
		Connection conn = null;
		String sql = "UPDATE TILE SET type = ?, contains_player = ? WHERE  gameboard_id = ? and row_index = ? and column_index = ?";
		
		try {
			conn = createConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			int rowIndex = 0;
			for (Tile[] tileRow : tiles) {
				int columnIndex = 0;
				
				for (Tile tile : tileRow) {
					stmt.setString(1, tile.getTileType().toString());
					stmt.setBoolean(2,  tile.getContainsPlayer());
					stmt.setInt(3, gameBoardId);
					stmt.setInt(4, rowIndex);
					stmt.setInt(5,  columnIndex);
					stmt.addBatch();
					
					columnIndex++;
				}
				rowIndex++;
			}
			
			stmt.executeBatch();
		} catch (SQLException e) {
			// java...
			e.printStackTrace();
		}

		/*
		 * public void save(List<Entity> entities) throws SQLException { try (
		 * Connection connection = database.getConnection(); PreparedStatement statement
		 * = connection.prepareStatement(SQL_INSERT); ) { int i = 0;
		 * 
		 * for (Entity entity : entities) { statement.setString(1,
		 * entity.getSomeProperty()); // ...
		 * 
		 * statement.addBatch(); i++;
		 * 
		 * if (i % 1000 == 0 || i == entities.size()) { statement.executeBatch(); //
		 * Execute every 1000 items. } } } }
		 */
	}
}
