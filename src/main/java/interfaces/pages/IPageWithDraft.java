package interfaces.pages;

import interfaces.tables.ITableWithDraft;


public interface IPageWithDraft extends IPage {

    ITableWithDraft getPageTable();
}
