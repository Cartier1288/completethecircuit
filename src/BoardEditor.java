import javax.swing.JPanel;

public class BoardEditor extends JPanel {
	private static final long serialVersionUID = -5143421163617225333L;

	public BoardEditor(Board board) {
		this.board = board;
	}
	
	Board board = null;
}
