import { ITableSnapshot } from 'app/shared/model/table-snapshot.model';

export interface IPot {
  id?: number;
  potChips?: number;
  potPlayersSeatNumbers?: string;
  tableSnapshot?: ITableSnapshot;
}

export const defaultValue: Readonly<IPot> = {};
