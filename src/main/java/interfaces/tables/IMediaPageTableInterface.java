package interfaces.tables;

import elements.rows.MediaPageTableRow;

public interface IMediaPageTableInterface extends ITable{

    MediaPageTableRow getRowByTitle(String rowTitle);
}
