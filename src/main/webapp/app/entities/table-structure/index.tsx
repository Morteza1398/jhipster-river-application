import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TableStructure from './table-structure';
import TableStructureDetail from './table-structure-detail';
import TableStructureUpdate from './table-structure-update';
import TableStructureDeleteDialog from './table-structure-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TableStructureUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TableStructureUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TableStructureDetail} />
      <ErrorBoundaryRoute path={match.url} component={TableStructure} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TableStructureDeleteDialog} />
  </>
);

export default Routes;
