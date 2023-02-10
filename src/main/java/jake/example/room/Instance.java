package jake.example.room;

import java.io.IOException;

import game.time.TIME;
import init.paths.PATHS;
import script.SCRIPT;
import settlement.main.SETT;
import snake2d.MButt;
import snake2d.Renderer;
import snake2d.util.datatypes.COORDINATE;
import snake2d.util.file.*;
import snake2d.util.misc.ACTION;
import snake2d.util.rnd.RND;
import util.gui.misc.GBox;
import view.interrupter.IDebugPanel;
import view.keyboard.KEYS;
import view.main.MessageText;

final class Instance implements SCRIPT.SCRIPT_INSTANCE {

	private int day = 16 + RND.rInt(16*10);
	private boolean hasMess;
	
	public Instance() {
		IDebugPanel.add("Script Eclipse", new ACTION() {
			
			@Override
			public void exe() {
				day = TIME.days().bitsSinceStart()+1;
			}
		});
	}
	
	
	@Override
	public void save(FilePutter file) {
		file.i(day);
	}

	@Override
	public void load(FileGetter file) throws IOException {
		day = file.i();
	}

	
	@Override
	public void update(double ds) {
		
		if (TIME.days().bitsSinceStart() == day && TIME.light().dayIs()) {
			if (!hasMess) {
				new MessageText(new Json(PATHS.SCRIPT().text.get("EXAMPLE")).json("ECLIPSE_MESSAGE")).send();
				hasMess = true;
			}
			double p = TIME.light().partOfCircular();
			if (p > 0.5) {
				p = Math.sqrt((p-0.5)*2);
				double r = 1.0-0.75*p;
				double o = 1.0-0.85*p;
				SETT.WEATHER().lightColor().set(r, o, o);
			
			}
			
		}else {
			if (TIME.days().bitsSinceStart() == day + 1) {
				day = TIME.days().bitsSinceStart() + 16 + RND.rInt(16*10);
			}
				
			hasMess = false;
		}

	}
	
	@Override
	public void hoverTimer(double mouseTimer, GBox text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Renderer r, float ds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPush(KEYS key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClick(MButt button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hover(COORDINATE mCoo, boolean mouseHasMoved) {
		// TODO Auto-generated method stub

	}

}