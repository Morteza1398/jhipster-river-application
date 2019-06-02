import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './table-structure.reducer';
import { ITableStructure } from 'app/shared/model/table-structure.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITableStructureProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class TableStructure extends React.Component<ITableStructureProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { tableStructureList, match } = this.props;
    return (
      <div>
        <h2 id="table-structure-heading">
          Table Structures
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Table Structure
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Big Blind</th>
                <th>Rake Policy</th>
                <th>Number Of Seats</th>
                <th>Min Buy In</th>
                <th>Max Buy In</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tableStructureList.map((tableStructure, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${tableStructure.id}`} color="link" size="sm">
                      {tableStructure.id}
                    </Button>
                  </td>
                  <td>{tableStructure.bigBlind}</td>
                  <td>{tableStructure.rakePolicy}</td>
                  <td>{tableStructure.numberOfSeats}</td>
                  <td>{tableStructure.minBuyIn}</td>
                  <td>{tableStructure.maxBuyIn}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tableStructure.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tableStructure.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tableStructure.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ tableStructure }: IRootState) => ({
  tableStructureList: tableStructure.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TableStructure);
