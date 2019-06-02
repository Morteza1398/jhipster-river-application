import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TableSnapshot from './table-snapshot';
import TableSnapshotDetail from './table-snapshot-detail';
import TableSnapshotUpdate from './table-snapshot-update';
import TableSnapshotDeleteDialog from './table-snapshot-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TableSnapshotUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TableSnapshotUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TableSnapshotDetail} />
      <ErrorBoundaryRoute path={match.url} component={TableSnapshot} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TableSnapshotDeleteDialog} />
  </>
);

export default Routes;
