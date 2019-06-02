import { ITableSnapshot } from 'app/shared/model/table-snapshot.model';

export const enum PlayerState {
  EMPTY = 'EMPTY',
  RESERVE = 'RESERVE',
  WAIT_FOR_BB = 'WAIT_FOR_BB',
  WANT_TO_PAY_BB = 'WANT_TO_PAY_BB',
  NORMAL = 'NORMAL',
  ALL_IN = 'ALL_IN',
  FOLDED = 'FOLDED',
  CAN_MOCK = 'CAN_MOCK',
  WAIT_FOR_LEAVE = 'WAIT_FOR_LEAVE',
  CAN_REBUY = 'CAN_REBUY',
  SITOUT = 'SITOUT'
}

export interface IPlayer {
  id?: number;
  username?: string;
  state?: PlayerState;
  disconnect?: boolean;
  wantToLeave?: boolean;
  satOutHands?: number;
  seatNumber?: number;
  tableRest?: number;
  handRest?: number;
  chipInStage?: number;
  cards?: string;
  tableSnapshot?: ITableSnapshot;
}

export const defaultValue: Readonly<IPlayer> = {
  disconnect: false,
  wantToLeave: false
};
