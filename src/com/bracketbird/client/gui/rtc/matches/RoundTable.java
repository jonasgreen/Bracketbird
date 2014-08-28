package com.bracketbird.client.gui.rtc.matches;


import com.bracketbird.client.model.tournament.Match;
import com.bracketbird.client.model.tournament.Round;
import com.bracketbird.client.table.Table;
import com.bracketbird.client.table.TableManager;
import com.bracketbird.client.table.TableRow;

/**
 *
 */
public class RoundTable extends Table {

    public RoundTable(TableManager tm, Round round, boolean withHeader) {
        super(tm);
        if (withHeader) {
           // addHeaderRow(new MatchHeaderRow(this));
        }
        for (Match match : round.getMatches()) {
            addTableRow(new MatchTableRow(this, match));
        }
    }

	@Override
	public void backspace(TableRow row, int cellIndex) {
		//ignore
		
	}

	@Override
	public void enter(TableRow row, int cellIndex) {
		down(row, cellIndex);
	}
}
