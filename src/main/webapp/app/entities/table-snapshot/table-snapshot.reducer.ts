import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITableSnapshot, defaultValue } from 'app/shared/model/table-snapshot.model';

export const ACTION_TYPES = {
  FETCH_TABLESNAPSHOT_LIST: 'tableSnapshot/FETCH_TABLESNAPSHOT_LIST',
  FETCH_TABLESNAPSHOT: 'tableSnapshot/FETCH_TABLESNAPSHOT',
  CREATE_TABLESNAPSHOT: 'tableSnapshot/CREATE_TABLESNAPSHOT',
  UPDATE_TABLESNAPSHOT: 'tableSnapshot/UPDATE_TABLESNAPSHOT',
  DELETE_TABLESNAPSHOT: 'tableSnapshot/DELETE_TABLESNAPSHOT',
  RESET: 'tableSnapshot/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITableSnapshot>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TableSnapshotState = Readonly<typeof initialState>;

// Reducer

export default (state: TableSnapshotState = initialState, action): TableSnapshotState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TABLESNAPSHOT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TABLESNAPSHOT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TABLESNAPSHOT):
    case REQUEST(ACTION_TYPES.UPDATE_TABLESNAPSHOT):
    case REQUEST(ACTION_TYPES.DELETE_TABLESNAPSHOT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TABLESNAPSHOT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TABLESNAPSHOT):
    case FAILURE(ACTION_TYPES.CREATE_TABLESNAPSHOT):
    case FAILURE(ACTION_TYPES.UPDATE_TABLESNAPSHOT):
    case FAILURE(ACTION_TYPES.DELETE_TABLESNAPSHOT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TABLESNAPSHOT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TABLESNAPSHOT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TABLESNAPSHOT):
    case SUCCESS(ACTION_TYPES.UPDATE_TABLESNAPSHOT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TABLESNAPSHOT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/table-snapshots';

// Actions

export const getEntities: ICrudGetAllAction<ITableSnapshot> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TABLESNAPSHOT_LIST,
  payload: axios.get<ITableSnapshot>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITableSnapshot> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TABLESNAPSHOT,
    payload: axios.get<ITableSnapshot>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITableSnapshot> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TABLESNAPSHOT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITableSnapshot> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TABLESNAPSHOT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITableSnapshot> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TABLESNAPSHOT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
