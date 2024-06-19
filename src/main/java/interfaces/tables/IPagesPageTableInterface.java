package interfaces.tables;

import elements.rows.PagesPageTableRow;

public interface IPagesPageTableInterface extends ITable{

    PagesPageTableRow getRowByTitle(String rowTitle);

    boolean isTitleDraft(String title);
}
